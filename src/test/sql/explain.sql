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