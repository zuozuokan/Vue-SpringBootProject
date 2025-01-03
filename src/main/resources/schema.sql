-- 教师表 (Teacher)
CREATE TABLE if not exists Teacher (
       id char(19) PRIMARY KEY,         -- 教师ID，主键
       name VARCHAR(20) NOT NULL,                      -- 姓名
       email VARCHAR(25) UNIQUE NULL,              -- 邮箱
       phone VARCHAR(15),                         -- 联系电话
       major VARCHAR(20),                               -- 专业
       account VARCHAR(30) UNIQUE NOT NULL,   -- 账号
       password CHAR(60) NOT NULL,   -- 密码
       role char(10) not null,					-- 角色
       create_time datetime default current_timestamp, -- 创建时间
       update_time datetime DEFAULT current_timestamp on update current_timestamp, -- 更新时间
       web_name varchar(20) null

);

-- 课程表 (Course)
CREATE TABLE if not exists Course (
      id char(19) PRIMARY KEY,         -- 课程ID，主键
      hours INT NOT NULL,                              -- 课程学时
      experiment_hours INT NOT NULL,                   -- 实验学时
      name VARCHAR(20) NOT NULL,               -- 课程名称
      description TEXT                                 -- 课程描述
);

-- 教师课程表 (TeacherCourse)
CREATE TABLE if not exists TeacherCourse (
     teacher_account char(19) NOT NULL,                          -- 教师工号
     course_name varchar(25),
     course_id char(19) NOT NULL,                           -- 课程ID
     semester VARCHAR(15) NOT NULL,                  -- 学期
     course_nums INT DEFAULT 0,                        -- 选课人数
     current_class VARCHAR(30),                              -- 上课班级
     hour INT,                              -- 课程学时
     experiment_hours INT,                   -- 实验学时
     PRIMARY KEY (teacher_account, course_id),              -- 复合主键
     FOREIGN KEY (teacher_account) REFERENCES Teacher(account),
     FOREIGN KEY (course_id) REFERENCES Course(id),
     INDEX (teacher_account)

);

-- 实验室负责人员表 (LabManage)
CREATE TABLE if not exists LabManage (
     id char(19) PRIMARY KEY,          -- 负责人员ID，主键
     name VARCHAR(20) NOT NULL,                      -- 姓名
     position VARCHAR(20) ,                            -- 职位
     email VARCHAR(25) UNIQUE null,              -- 邮箱
     phone VARCHAR(15) null                       -- 联系电话
);

-- 实验室表 (Laboratory)
CREATE TABLE if not exists Lab (
       id char(19) PRIMARY KEY,     -- 实验室ID，主键
       name varchar(30) not null ,                          -- 实验室名称
       staff_id char(19) NOT NULL,                            -- 负责人ID
       capacity INT NULL,                           -- 实验室容量
       configuration TEXT,                              -- 实验室配置介绍
       status ENUM('Available', 'NotAvailable', 'Maintenance') DEFAULT 'Available', -- 实验室状态
       FOREIGN KEY (staff_id) REFERENCES LabManage(id),
       INDEX (capacity)
);


-- 预约表 (Reservation)
CREATE TABLE IF NOT EXISTS Reservation (
   id int PRIMARY KEY auto_increment,   -- 预约ID，主键
   teacher_account varchar(20) NOT NULL,        -- 教师ID
   course_id char(19) NOT NULL,                  -- 课程ID
   lab_id char(19) NOT NULL,                     -- 实验室ID
   teacher_name varchar(20),                     -- 教师姓名
   course_name varchar(25),                      -- 课程名称
   temp_reservation char(6) DEFAULT FALSE,       -- 是否为临时预约
   reservation_date datetime default current_timestamp, -- 预约创建日期
   specifics JSON NOT NULL COMMENT '{"week", "period"}', -- 预约的周数星期及节次 (JSON 类型)
   week varchar(5) NOT NULL,                     -- 预约的周次
   unique((cast(Specifics ->> '$.week' as char(5)) collate utf8mb4_bin),
       (cast(Specifics ->> '$.period' as char(8)) collate utf8mb4_bin),
           week,lab_id),
   index(teacher_account),
   index(course_id)

);


