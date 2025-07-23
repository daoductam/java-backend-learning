import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 10, // 10 người dùng đồng thời
  duration: '10s', // trong vòng 10 giây
};

export default function () {
  const url = 'http://localhost:8080/api/orders';
  const payload = JSON.stringify({
    productName: 'Sản phẩm A',
    customerName: 'Nguyễn Văn A',
    quantity: 2,
    email: "a@gmail.com",
    price: 100000
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(url, payload, params);

  check(res, {
    'status is 201': (r) => r.status === 201,
  });

  sleep(1);
}
