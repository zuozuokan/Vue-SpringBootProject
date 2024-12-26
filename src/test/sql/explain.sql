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

update Teacher t set t.web_name = 'zuozuokan', t.email = 'zzz@163.com', t.phone = '123456' where t.account = '2022212968'

SELECT * FROM TeacherCourse WHERE teacher_account = '2022212968'


INSERT INTO TeacherCourse(teacher_account,course_id,semester,course_nums,current_class,course_Name)
VALUES ('2022212968','C007','2025-2026','23','1班','大学物理')

DELETE FROM TeacherCourse WHERE teacher_account = '2022212968' AND course_id = 'C006'


SELECT * FROM Lab l WHERE l.capacity >= 35

SELECT * FROM TeacherCourse WHERE teacher_account = '2022212968' AND course_id = 'C009'

INSERT INTO Reservation (teacher_account, course_id,lab_id,teacher_name,course_name,temp_reservation, specifics, week)
VALUES ('2022212968', 'C001', 'L001','zuozuokan', '高数', '1', '{"week": "周一", "period": "1-2"}', 6)

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

SELECT r.*,l.name FROM Reservation r join Lab l on r.lab_id = l.id WHERE r.teacher_account = '2022212968'

DELETE FROM Reservation r WHERE r.teacher_account = '2022212968' and JSON_EXTRACT(r.Specifics, '$.week') = '周四' and r.lab_id = 'L001' and JSON_EXTRACT(r.Specifics, '$.period') = '1-2' and week = 4;

SELECT * FROM Reservation r
WHERE JSON_EXTRACT(r.Specifics, '$.week') = '周四'
  AND JSON_EXTRACT(r.Specifics, '$.period') = '1-2';

INSERT INTO Reservation (Teacher_Account, Course_ID, Lab_ID, Teacher_Name, Course_Name, Temp_Reservation, Reservation_Date, Specifics, Week)
VALUES
    ('2022212968', 'C001', 'L001', '李四', '计算机网络', '0', '2024-12-26 12:52:13', '{"week": "周一", "period": "1-2"}', '1'),
    ('2022212968', 'C002', 'L002', '李四', '线性代数', '0', '2024-12-26 12:52:13', '{"week": "周二", "period": "3-4"}', '2'),
    ('2022212968', 'C003', 'L003', '李四', '操作系统', '1', '2024-12-26 12:55:36', '{"week": "周三", "period": "5-6"}', '3'),
    ('2022212968', 'C004', 'L001', '李四', '计算机组成原理', '1', '2024-12-26 13:00:00', '{"week": "周四", "period": "7-8"}', '4'),
    ('2022212968', 'C005', 'L002', '李四', '高等数学', '0', '2024-12-26 13:10:00', '{"week": "周五", "period": "1-2"}', '5'),
    ('2022212968', 'C001', 'L003', '李四', '计算机网络', '0', '2024-12-26 13:20:00', '{"week": "周一", "period": "3-4"}', '6'),
    ('2022212968', 'C002', 'L001', '李四', '线性代数', '1', '2024-12-26 13:30:00', '{"week": "周二", "period": "5-6"}', '7'),
    ('2022212968', 'C003', 'L002', '李四', '操作系统', '0', '2024-12-26 13:40:00', '{"week": "周三", "period": "1-2"}', '8'),
    ('2022212968', 'C004', 'L003', '李四', '计算机组成原理', '1', '2024-12-26 13:50:00', '{"week": "周四", "period": "3-4"}', '9'),
    ('2022212968', 'C005', 'L001', '李四', '高等数学', '0', '2024-12-26 14:00:00', '{"week": "周五", "period": "5-6"}', '10');


