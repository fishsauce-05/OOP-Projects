\# Parking Lot Management System



\## 1. Giới thiệu (Overview)

Hệ thống quản lý vận hành bãi đỗ xe cho một tòa nhà thương mại quy mô lớn. Hệ thống được thiết kế để phục vụ nhiều tầng hầm, nhiều cổng ra vào và đa dạng các loại phương tiện (xe máy, ô tô, xe tải, xe ưu tiên).



Mục đích chính là tự động hóa quy trình gửi xe, tối ưu hóa việc sử dụng chỗ đỗ và cung cấp khả năng tính phí linh hoạt.



\## 2. Mục tiêu (Objectives)

\* \*\*Tự động hóa:\*\* Giảm thao tác thủ công và sai sót trong quy trình xe vào/ra.

\* \*\*Kiểm soát \& An ninh:\*\* Ghi nhận chính xác mọi lượt xe và trạng thái thực tế của bãi.

\* \*\*Tối ưu hóa tài nguyên:\*\* Quản lý hiệu quả các điểm đỗ (spot), tránh quá tải không kiểm soát.

\* \*\*Tính phí linh hoạt:\*\* Hỗ trợ nhiều chiến lược giá (theo giờ, theo loại xe, vé tháng...).

\* \*\*Hỗ trợ vận hành:\*\* Cung cấp số liệu thống kê doanh thu và trạng thái cho quản lý.



\## 3. Phạm vi dự án (Scope)



\### Trong phạm vi (In Scope)

\* Quản lý logic xe vào/ra (Entry/Exit flow).

\* Quản lý cấu trúc bãi đỗ: Nhiều tầng (Levels), nhiều cổng (Entry/Exit Gates).

\* Quản lý trạng thái chỗ đỗ (Parking Spot): Occupied, Free, Reserved.

\* Phân loại phương tiện \& Chỗ đỗ: Car, Bike, Truck, Handicapped, Monthly/VIP.

\* Hệ thống tính phí (Billing) và Thanh toán (Payment giả lập).

\* Quản lý vé tháng (Monthly Subscription) cơ bản.



\### Ngoài phạm vi (Out of Scope)

\* Tích hợp phần cứng thực tế (Camera nhận diện biển số, Barrier tự động, Cảm biến).

\* Cổng thanh toán ngân hàng thực tế (Banking integration).

\* Các tính năng bảo mật hệ thống chuyên sâu.



\## 4. Luồng hoạt động (Workflows)



\### 4.1. Luồng vào (Entry)

1\.  Xe đến cổng vào (Entry Gate).

2\.  Hệ thống xác định loại xe và kiểm tra sức chứa khả dụng.

3\.  \*\*Nếu còn chỗ:\*\* Sinh vé (Ticket ID, thời gian vào, loại xe) $\\rightarrow$ Mở Barrier.

4\.  \*\*Nếu hết chỗ:\*\* Thông báo từ chối phục vụ.



\### 4.2. Luồng ra (Exit)

1\.  Xe đến cổng ra (Exit Gate) và xuất trình vé.

2\.  Hệ thống xác thực vé, tính toán thời gian gửi.

3\.  Tính phí dựa trên \*\*Pricing Strategy\*\* (loại xe, thời gian, ngày thường/lễ).

4\.  Khách hàng thanh toán (Tiền mặt/Thẻ).

5\.  Thanh toán thành công $\\rightarrow$ Giải phóng chỗ đỗ $\\rightarrow$ Mở Barrier.



\## 5. Yêu cầu chức năng (Functional Requirements)



\* \*\*Quản lý tầng \& chỗ đỗ:\*\* Hỗ trợ nhiều tầng, phân loại spot (Regular, Compact, Handicapped).

\* \*\*Quản lý cổng:\*\* Entry và Exit gates hoạt động song song.

\* \*\*Tính phí:\*\* Áp dụng Strategy Pattern để thay đổi cách tính phí dễ dàng.

\* \*\*Vé tháng:\*\* Hệ thống nhận diện khách hàng vé tháng để bỏ qua bước tính phí lượt.

\* \*\*Dashboard:\*\* Admin có thể cấu hình số lượng spot và xem trạng thái bãi.



\## 6. Actors \& Use Cases



| Actor | Hành động (Action) | Mô tả |

| :--- | :--- | :--- |

| \*\*Admin\*\* | Cấu hình hệ thống | Thêm tầng, thêm cổng, định nghĩa loại spot. |

| | Xem báo cáo | Xem doanh thu, thống kê lượt xe. |

| \*\*Khách hàng\*\* | Lấy vé / Quẹt thẻ | Tương tác tại cổng vào. |

| | Thanh toán | Trả phí tại cổng ra hoặc máy thanh toán. |

| | Đăng ký vé tháng | Mua/Gia hạn gói gửi xe cố định. |

| \*\*Hệ thống\*\* | Kiểm tra chỗ trống | Tự động tính toán capacity khả dụng. |

| | Tính phí (Billing) | Tính tiền dựa trên thời gian thực tế. |

| | Điều khiển Barrier | Giả lập việc đóng/mở cổng. |



\## 7. Thiết kế kỹ thuật (Technical Highlights)

\* \*\*OOP Principles:\*\* Áp dụng triệt để các nguyên lý hướng đối tượng.

\* \*\*Design Patterns:\*\*

&nbsp;   \* \*Strategy Pattern:\* Cho phần tính giá (Pricing).

&nbsp;   \* \*Factory Pattern:\* (Gợi ý) Để khởi tạo các loại Vehicle/Spot.

