-- 查询对应实验室信息
explain
SELECT * FROM Lab WHERE id = 'L001';

-- 获取所有实验室的id和name(只能全表扫描)
explain
SELECT id, name FROM Lab;

-- 查询对应实验室预约情况
explain
SELECT * FROM Reservation r WHERE r.lab_id = 'L002' AND r.week = '3';

-- 基于实验室id查询对应负责人
explain
SELECT lm.name,lm.email,lm.position,lm.phone FROM Lab l join LabManage lm on l.staff_id = lm.id
where l.id = 'L001';

-- 根据教师ID查询教师
explain
SELECT * FROM Teacher t WHERE t.id = '1320323624675344384';

-- 根据教师账号/工号查询教师
explain
SELECT * FROM Teacher t WHERE t.account = '2022212968';

-- 找到所有教师(必须全表扫描)
explain
SELECT * FROM Teacher;

update Teacher t set t.web_name = 'zuozuokan', t.email = 'zzz@163.com', t.phone = '123456' where t.account = '2022212968';

SELECT * FROM TeacherCourse WHERE teacher_account = '2022212968';


INSERT INTO TeacherCourse(teacher_account,course_id,semester,course_nums,current_class,course_Name)
VALUES ('2022212968','C007','2025-2026','23','1班','大学物理');

DELETE FROM TeacherCourse WHERE teacher_account = '2022212968' AND course_id = 'C006';


SELECT * FROM Lab l WHERE l.capacity >= 35;

SELECT * FROM TeacherCourse WHERE teacher_account = '2022212968' AND course_id = 'C009';

INSERT INTO Reservation (teacher_account, course_id,lab_id,teacher_name,course_name,temp_reservation, specifics, week)
VALUES ('2022212968', 'C001', 'L001','zuozuokan', '高数', '1', '{"week": "周一", "period": "1-2"}', 6);

INSERT INTO Reservation (Teacher_Account, Course_ID, Lab_ID, Teacher_Name, Course_Name, Temp_Reservation, Reservation_Date, Specifics, Week)
VALUES
    ('admin', 'C001', 'L001', '张三', '数学导论', FALSE, NOW(), '{"week": "周二", "period": "1-2"}', '第1周'),
    ('2022212968', 'C002', 'L002', '李四', '线性代数', FALSE, NOW(), '{"week": "周二", "period": "3-4"}', '第1周'),
    ('2022212969', 'C003', 'L003', '王五', '高等数学', FALSE, NOW(), '{"week": "周三", "period": "5-6"}', '第2周'),
    ('zhangsan', 'C004', 'L004', '张三', '计算机基础', FALSE, NOW(), '{"week": "周四", "period": "7-8"}', '第2周'),
    ('lisi', 'C005', 'L005', '李四', '数据结构', TRUE, NOW(), '{"week": "周五", "period": "9-10"}', '第3周'),
    ('wangwu', 'C006', 'L006', '王五', '操作系统', TRUE, NOW(), '{"week": "周五", "period": "11-12"}', '第3周'),
    ('sunqi', 'C007', 'L007', '孙七', '软件工程', FALSE, NOW(), '{"week": "周二", "period": "1-2"}', '第4周'),
    ('admin', 'C008', 'L008', '张三', '数据库原理', FALSE, NOW(), '{"week": "周三", "period": "3-4"}', '第4周'),
    ('2022212968', 'C001', 'L001', '李四', '计算机网络', TRUE, NOW(), '{"week": "周四", "period": "5-6"}', '第5周'),
    ('2022212969', 'C002', 'L002', '王五', '人工智能', TRUE, NOW(), '{"week": "周五", "period": "7-8"}', '第5周');

SELECT r.*,l.name FROM Reservation r join Lab l on r.lab_id = l.id WHERE r.teacher_account = '2022212968';

DELETE FROM Reservation r WHERE r.teacher_account = '2022212968' and JSON_EXTRACT(r.Specifics, '$.week') = '周四' and r.lab_id = 'L001' and JSON_EXTRACT(r.Specifics, '$.period') = '1-2' and week = 4;


SELECT * FROM Reservation r
WHERE JSON_EXTRACT(r.Specifics, '$.week') = '周四'
  AND JSON_EXTRACT(r.Specifics, '$.period') = '1-2';

# INSERT INTO Reservation (Teacher_Account, Course_ID, Lab_ID, Teacher_Name, Course_Name, Temp_Reservation, Reservation_Date, Specifics, Week)
# VALUES
#     ('2022212968', 'C001', 'L001', '李四', '计算机网络', '0', '2024-12-26 12:52:13', '{"week": "周一", "period": "1-2"}', '1'),
#     ('2022212968', 'C002', 'L002', '李四', '线性代数', '0', '2024-12-26 12:52:13', '{"week": "周二", "period": "3-4"}', '2'),
#     ('2022212968', 'C003', 'L003', '李四', '操作系统', '1', '2024-12-26 12:55:36', '{"week": "周三", "period": "5-6"}', '3'),
#     ('2022212968', 'C004', 'L001', '李四', '计算机组成原理', '1', '2024-12-26 13:00:00', '{"week": "周四", "period": "7-8"}', '4'),
#     ('2022212968', 'C005', 'L002', '李四', '高等数学', '0', '2024-12-26 13:10:00', '{"week": "周五", "period": "1-2"}', '5'),
#     ('2022212968', 'C001', 'L003', '李四', '计算机网络', '0', '2024-12-26 13:20:00', '{"week": "周一", "period": "3-4"}', '6'),
#     ('2022212968', 'C002', 'L001', '李四', '线性代数', '1', '2024-12-26 13:30:00', '{"week": "周二", "period": "5-6"}', '7'),
#     ('2022212968', 'C003', 'L002', '李四', '操作系统', '0', '2024-12-26 13:40:00', '{"week": "周三", "period": "1-2"}', '8'),
#     ('2022212968', 'C004', 'L003', '李四', '计算机组成原理', '1', '2024-12-26 13:50:00', '{"week": "周四", "period": "3-4"}', '9'),
#     ('2022212968', 'C005', 'L001', '李四', '高等数学', '0', '2024-12-26 14:00:00', '{"week": "周五", "period": "5-6"}', '10');
#

# 查询某个老师的所有预约
explain
SELECT r.*,l.name FROM Reservation r join Lab l on r.lab_id = l.id WHERE r.teacher_account = '2022212968';


# 查询是否重复
explain
select * from Reservation r where Week = '1' and Specifics ->> '$.week' = '周二' and Specifics ->> '$.period' = '1-2' and Lab_ID = 'L001';

insert into Lab (id, name, staff_Id, capacity, configuration, status)
values ('L110', '丹青922', 'L001', '100', '高级配置', 'Available');

INSERT INTO LabManage (id,name,email,position,phone) VALUES ('L111','超级赛亚人','','','');

INSERT INTO Course (id,hours,experiment_hours,name,description)
VALUES ('C111',40,16,'高等数学','这是一门重要的数学课程');

#####################################################插入用例###################################################################
# LabManage 表 - 插入实验室负责人员
INSERT INTO LabManage (id, Name, Position, Email, Phone) VALUES
 ('2022212968', '马云', 'CEO', 'ma@alibaba.com', '13800138000'),
 ('2022212969', '雷军', 'CEO', 'lei@xiaomi.com', '13800138001'),
 ('2022212970', '马化腾', 'CEO', 'ma@tencent.com', '13800138002'),
 ('2022212971', '任正非', 'CEO', 'ren@huawei.com', '13800138003'),
 ('2022212972', '刘强东', 'CEO', 'liu@jd.com', '13800138004'),
 ('2022212973', '李彦宏', 'CEO', 'li@baidu.com', '13800138005'),
 ('2022212974', '杨致远', '创始人', 'yang@yahoo.com', '13800138006'),
 ('2022212976', '张朝阳', 'CEO', 'zhang@sohu.com', '13800138007'),
 ('2022212977', '丁磊', 'CEO', 'ding@netease.com', '13800138008'),
 ('2022212978', '曹国伟', 'CEO', 'cao@weibo.com', '13800138009');

#########teacher表已插入##########

# Course 表 - 插入课程数据
INSERT INTO Course (id, Hours, Experiment_Hours, Name, Description) VALUES
    ('C001', 40, 20, '数据结构与算法', '学习常见的数据结构和算法。'),
    ('C002', 60, 30, '计算机网络', '计算机网络的基本原理与应用。'),
    ('C003', 45, 20, '操作系统', '计算机操作系统的工作原理。'),
    ('C004', 50, 25, '数据库系统', '学习数据库的设计与管理。'),
    ('C005', 30, 10, '编程语言', '学习基本的编程语言原理。'),
    ('C006', 36, 18, '计算机图形学', '计算机图形学及其应用。'),
    ('C007', 40, 20, '人工智能', '人工智能的基础知识与应用。'),
    ('C008', 60, 30, '软件工程', '软件开发的基本流程和方法。'),
    ('C009', 50, 25, '信息安全', '计算机安全与防护技术。'),
    ('C010', 45, 20, '操作系统课程设计', '操作系统设计与开发的实践课程。');

# TeacherCourse 表 - 插入教师课程关系数据
INSERT INTO TeacherCourse (Teacher_Account, Course_Name, Course_ID, Semester, Course_Nums, Current_Class, Hours, Experiment_Hours) VALUES
   ('2022212968', '数据结构与算法', 'C001', '2023-2024-1', 50, '计算机科学班', 40, 20),
   ('2022212969', '计算机网络', 'C002', '2023-2024-1', 40, '电子工程班', 60, 30),
   ('2022212970', '操作系统', 'C003', '2023-2024-2', 60, '计算机科学班', 45, 20),
   ('2022212971', '数据库系统', 'C004', '2023-2024-2', 45, '通信工程班', 50, 25),
   ('2022212972', '编程语言', 'C005', '2023-2024-2', 30, '电商管理班', 30, 10),
   ('2022212973', '计算机图形学', 'C006', '2023-2024-2', 35, '人工智能班', 36, 18),
   ('2022212974', '人工智能', 'C007', '2023-2024-2', 40, '计算机科学班', 40, 20),
   ('2022212976', '软件工程', 'C008', '2023-2024-1', 50, '互联网班', 60, 30),
   ('2022212977', '信息安全', 'C009', '2023-2024-1', 45, '计算机安全班', 50, 25),
   ('2022212978', '操作系统课程设计', 'C010', '2023-2024-1', 30, '操作系统班', 45, 20);
# Lab 表 - 插入实验室数据
INSERT INTO Lab (id, Name, Staff_ID, Capacity, Configuration, Status) VALUES
  ('L001', '丹青楼922', '2022212968', 50, '高性能计算机', 'Available'),
  ('L002', '丹青楼913', '2022212969', 40, '标准计算机', 'Available'),
  ('L003', '丹青楼914', '2022212970', 30, '虚拟机环境', 'Maintenance'),
  ('L004', '丹青楼915', '2022212971', 60, '嵌入式系统', 'Available'),
  ('L005', '丹青楼916', '2022212972', 35, '人工智能实验室', 'Available'),
  ('L006', '丹青楼917', '2022212973', 45, '数据科学实验室', 'Maintenance'),
  ('L007', '丹青楼918', '2022212974', 40, '大数据处理', 'Available'),
  ('L008', '丹青楼919', '2022212976', 50, '网络安全实验室', 'Available'),
  ('L009', '丹青楼920', '2022212977', 50, '多媒体技术', 'Available'),
  ('L010', '丹青楼921', '2022212978', 30, '区块链实验室', 'Available');

# Reservation 表 - 插入预约数据
INSERT INTO Reservation (Teacher_Account, Course_ID, Lab_ID, Teacher_Name, Course_Name, Temp_Reservation, Reservation_Date, Specifics, Week) VALUES
 ('2022212968', 'C001', 'L001', '马云', '数据结构与算法', 'FALSE', NOW(), '{"week": "周一", "period": "1-2"}', '1'),
 ('2022212969', 'C002', 'L002', '雷军', '计算机网络', 'FALSE', NOW(), '{"week": "周二", "period": "3-4"}', '2'),
 ('2022212970', 'C003', 'L003', '马化腾', '操作系统', 'FALSE', NOW(), '{"week": "周三", "period": "5-6"}', '3'),
 ('2022212971', 'C004', 'L004', '任正非', '数据库系统', 'FALSE', NOW(), '{"week": "周四", "period": "7-8"}', '4'),
 ('2022212972', 'C005', 'L005', '刘强东', '编程语言', 'FALSE', NOW(), '{"week": "周五", "period": "9-10"}', '5'),
 ('2022212973', 'C006', 'L006', '李彦宏', '计算机图形学', 'FALSE', NOW(), '{"week": "周六", "period": "11-12"}', '6'),
 ('2022212974', 'C007', 'L007', '杨致远', '人工智能', 'FALSE', NOW(), '{"week": "周日", "period": "1-2"}', '7'),
 ('2022212976', 'C008', 'L008', '张朝阳', '软件工程', 'FALSE', NOW(), '{"week": "周一", "period": "3-4"}', '8'),
 ('2022212977', 'C009', 'L009', '丁磊', '信息安全', 'FALSE', NOW(), '{"week": "周二", "period": "5-6"}', '9'),
 ('2022212978', 'C010', 'L010', '曹国伟', '操作系统课程设计', 'FALSE', NOW(), '{"week": "周三", "period": "7-8"}', '10');

explain
SELECT * FROM Lab l WHERE l.capacity >= 20;

