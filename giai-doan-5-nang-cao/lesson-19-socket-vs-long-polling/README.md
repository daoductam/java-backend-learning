# Websocket vs Long polling
## HTTP Polling thông thường.
### 1. Khái niệm
HTTP Polling thông thường là kỹ thuật client liên tục gửi request đến server định kỳ để hỏi xem dữ liệu mới có xuất hiện chưa. Server trả response ngay cả khi dữ liệu chưa thay đổi (thường trả rỗng hoặc trạng thái hiện tại).
- Ví dụ: client hỏi server mỗi 5 giây: “Có trạng thái đơn hàng mới không?”
### 2. Cơ chế hoạt động
- 1.Client gửi HTTP GET request đến server.
- 2.Server trả dữ liệu (mới hoặc rỗng) ngay lập tức.
- 3.Client chờ khoảng thời gian đã định (ví dụ 5s) rồi gửi request mới.
- 4.Vòng lặp này lặp lại liên tục.

Sơ đồ mô tả:
Client                         Server
  |  --- GET /status ----->     |
  |                            |
  | <--- Response (status) --- |
  |                            |
[wait 5s]                      |
  |  --- GET /status ----->     |
  |                            |
  | <--- Response (status) --- |
  ...

### 3. Ưu điểm
- Rất đơn giản, dễ triển khai.
- Không cần kết nối lâu dài hay WebSocket.
- Hoạt động tốt nếu dữ liệu thay đổi ít và không cần realtime.

### 4. Nhược điểm
- Hiệu suất thấp: nhiều request HTTP → overhead cao (header, handshake, TCP connection).
- Latency cao: nếu client gửi request mỗi 5s, dữ liệu mới có thể phải chờ tối đa 5s mới nhận được.
- Khó mở rộng: khi có nhiều client, server phải xử lý lượng lớn request định kỳ. 
### 5. Ví dụ code (JavaScript – client)
Mô tả: client cứ mỗi 5 giây gửi request GET /status và in ra trạng thái mới.

function pollStatus(orderId) {
  setInterval(async () => {
    try {
      const res = await fetch(`/api/v1/orders/status?orderId=${orderId}`);
      if (res.ok) {
        const data = await res.json();
        console.log('Status:', data.status);
      }
    } catch (err) {
      console.error('Polling error:', err);
    }
  }, 5000); // gửi request mỗi 5 giây
}

pollStatus('ORDER-12345');

### 6. Khi nào dùng HTTP Polling thông thường?
- Dữ liệu ít thay đổi (không cần realtime).
- Hạ tầng hoặc client không hỗ trợ WebSocket.
- Triển khai nhanh, demo hoặc prototyping.
- Ví dụ: 
    - Kiểm tra trạng thái upload file.
    - Kiểm tra email mới (ít thay đổi).
    - Dữ liệu dashboard không quan trọng realtime (cập nhật sau vài giây).

## Long Polling
### 1. Khái niệm
- Long Polling là kỹ thuật client gửi request HTTP đến server, nhưng server không trả response ngay nếu dữ liệu chưa thay đổi.
- Server sẽ giữ kết nối mở trong một khoảng thời gian (ví dụ 20 giây):
    - Nếu dữ liệu thay đổi → trả ngay response.
    - Nếu hết timeout mà dữ liệu không thay đổi → trả response rỗng hoặc 204 No Content.
    - Client nhận response → gửi request mới → vòng lặp tiếp tục.
- Mục tiêu: giảm độ trễ giữa khi dữ liệu thay đổi và client nhận được, mà vẫn dùng HTTP.
### 2. Cơ chế hoạt động
- Sơ đồ:
Client                         Server
  | --- GET /status --->       |
  |                            |
  | <--- Response (if change)  |
  |                            |
  | OR (timeout 20s) <---------|
  |                            |
[Immediately send new GET]     |
  | --- GET /status --->       |
  ...

- Khác với HTTP Polling thường: server giữ kết nối mở, không trả response ngay.
- Kết quả: client nhận dữ liệu gần realtime mà không phải gửi request quá thường xuyên.
### 3. Ưu điểm
- Latency thấp hơn polling thông thường → client nhận dữ liệu gần realtime.
- Giảm số lượng request HTTP so với polling định kỳ (không cần gửi request mỗi vài giây nếu dữ liệu chưa thay đổi).
- Vẫn sử dụng HTTP → dễ triển khai trên hạ tầng có firewall/proxy không hỗ trợ WebSocket.
### 4. Nhược điểm
- Vẫn tốn nhiều kết nối HTTP nếu nhiều client cùng lúc (server phải giữ nhiều kết nối chờ).
- Không thực sự full-duplex: server chỉ trả response, client vẫn phải lập lại request.
- Nếu server scale nhiều instance → phải dùng Redis Pub/Sub hoặc shared notifier để đồng bộ các client đang chờ.
### 5. Ví dụ code (Spring Boot – Java)

// OrderStatusController.java
@GetMapping("/status")
public DeferredResult<ResponseEntity<?>> getOrderStatus(@RequestParam String orderId) {
    // timeout 20 giây
    DeferredResult<ResponseEntity<?>> deferred = new DeferredResult<>(20_000L);
    
    // Timeout -> trả 204
    deferred.onTimeout(() -> deferred.setResult(ResponseEntity.noContent().build()));

    // Đăng ký vào notifier để khi trạng thái thay đổi, server setResult
    notifier.register(orderId, deferred);
    return deferred;
}

- DeferredResult giữ kết nối HTTP mở trong tối đa 20 giây.
- Khi status đơn hàng thay đổi, server gọi deferred.setResult(payload) → client nhận ngay.
- Nếu hết 20 giây mà chưa có cập nhật → trả 204 → client lập tức gửi request mới.
### 6. Client JavaScript

async function longPoll(orderId) {
  while(true) {
    try {
      const res = await fetch(`/api/v1/orders/status?orderId=${orderId}`);
      if (res.status === 200) {
        const data = await res.json();
        console.log('Order updated:', data.status);
      }
      // nếu 204 No Content, vòng lặp tiếp tục
    } catch (err) {
      console.error('Error:', err);
      await new Promise(r => setTimeout(r, 2000)); // backoff khi lỗi
    }
  }
}

longPoll('ORDER-12345');

- Client nhận dữ liệu gần realtime, không cần gửi request liên tục.
- Khi server gửi update → client nhận ngay.
- Khi timeout → client gửi request mới → vòng lặp tiếp tục.

### 7. Khi nào dùng Long Polling?
- Muốn gần realtime, nhưng hạ tầng không cho WebSocket.
- Dữ liệu thay đổi không quá nhanh, số lượng client vừa phải.
- Ví dụ thực tế:
    - Order Tracking (Shopee-like): cập nhật trạng thái đơn hàng.
    - Notification: thông báo mới cho user nếu không dùng WebSocket.
    - Chat đơn giản: ứng dụng nhỏ, ít user đồng thời.

## WebSocket
### 1. Khái niệm
- WebSocket là kết nối TCP 2 chiều persistent giữa client và server:
    - Sau khi handshake HTTP/HTTPS ban đầu, kết nối được nâng cấp lên WebSocket.
    - Server có thể gửi dữ liệu bất kỳ lúc nào tới client.
    - Client cũng có thể gửi dữ liệu bất kỳ lúc nào tới server.
    - Thích hợp cho realtime, nhiều client cùng theo dõi dữ liệu, chat, game, live updates.
- Điểm khác biệt lớn so với Long Polling:
| Khía cạnh   | Long Polling                                  | WebSocket                         |
| ----------- | --------------------------------------------- | --------------------------------- |
| Kết nối     | HTTP request mở → server trả → client gửi lại | TCP connection persistent 1 lần   |
| Server push | Server chỉ trả khi client request             | Server push bất kỳ lúc nào        |
| Latency     | Thấp hơn polling nhưng vẫn có vòng lặp        | Rất thấp, realtime ngay lập tức   |
| Overhead    | HTTP headers nhiều                            | Rất ít, chỉ frame nhỏ cho dữ liệu |
### 2. Cơ chế hoạt động
- Handshake (HTTP → WS): client gửi request HTTP với header Upgrade: websocket.
- Server chấp nhận → kết nối TCP 2 chiều mở.
- Client và server trao đổi dữ liệu theo frame WebSocket, full-duplex.
- Kết nối giữ lâu dài cho tới khi client hoặc server đóng.
- Sơ đồ minh họa:
Client                         Server
  | --- HTTP GET /ws --->      |   // handshake
  |   Upgrade: websocket       |
  | <--- 101 Switching Protocols
  |                            |
  | <---- orderUpdated --------|   // server push ngay khi có update
  | ---- ack ----------------->|   // client gửi dữ liệu
  | ... trao đổi realtime ...  |
  | --- close ---------------->|

### 3. Ưu điểm
- Realtime, latency cực thấp: server push ngay lập tức.
- Tiết kiệm băng thông: không cần gửi header HTTP nhiều lần.
- Full-duplex: client & server đều có thể gửi dữ liệu bất kỳ lúc nào.
- Thích hợp cho số lượng client lớn (khi scale đúng hạ tầng).
### Nhược điểm / lưu ý
- Phải hạ tầng hỗ trợ kết nối lâu dài (firewall, proxy, load balancer).
- Nếu nhiều instance server → cần Redis adapter / PubSub để broadcast tới tất cả clients.
- Client không nhận dữ liệu nếu mất kết nối → cần fallback (ví dụ Long Polling).
- Cần heartbeat / ping-pong để giữ kết nối sống.
### Ví dụ cài đặt Node.js + Socket.IO
- Server (Node.js + Socket.IO)

const express = require('express');
const http = require('http');
const { Server } = require('socket.io');

const app = express();
app.use(express.json());

const server = http.createServer(app);
const io = new Server(server, {
  cors: { origin: '*' }
});

// Khi client connect
io.on('connection', (socket) => {
  console.log('Client connected', socket.id);

  // Client subscribe theo orderId
  socket.on('subscribeOrder', (orderId) => {
    socket.join(`order:${orderId}`);
    console.log(`Socket ${socket.id} joined order:${orderId}`);
  });

  socket.on('disconnect', () => {
    console.log('Client disconnected', socket.id);
  });
});

// API giả lập cập nhật order
app.post('/api/v1/orders/:orderId/update-status', (req, res) => {
  const { orderId } = req.params;
  const { status } = req.body;
  const payload = { orderId, status, updatedAt: new Date().toISOString() };
  
  // Emit tới tất cả client subscribe order này
  io.to(`order:${orderId}`).emit('orderUpdated', payload);
  res.json({ ok: true });
});

server.listen(3000, () => console.log('WebSocket server listening on 3000'));

- Client JavaScript

const socket = io('http://localhost:3000');

socket.on('connect', () => {
  console.log('WS connected');
  socket.emit('subscribeOrder', 'ORDER-12345');
});

socket.on('orderUpdated', (data) => {
  console.log('Order update received:', data);
  document.getElementById('status').innerText = data.status;
});

socket.on('disconnect', () => {
  console.log('WS disconnected');
});

- Server push ngay lập tức khi order status thay đổi.
- Client nhận dữ liệu realtime mà không cần gửi request nhiều lần.
### 6. Khi nào dùng WebSocket?
- Realtime data: chat, game, stock, order tracking.
- Nhiều client theo dõi cùng lúc, dữ liệu thay đổi liên tục.
- Cần low-latency updates và full-duplex communication.
- Ví dụ thực tế:
    - Shopee: cập nhật trạng thái đơn hàng realtime.
    - App chat: gửi/nhận tin nhắn ngay lập tức.
    - Game online: đồng bộ trạng thái nhiều người chơi.