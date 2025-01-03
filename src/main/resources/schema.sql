-- 教师表 (Teacher)
CREATE TABLE if not exists Teacher (
       id char(19) PRIMARY KEY,         -- 教师ID，主键
       Name VARCHAR(20) NOT NULL,                      -- 姓名
       Email VARCHAR(25) UNIQUE NULL,              -- 邮箱
       Phone VARCHAR(15),                         -- 联系电话
       Major VARCHAR(20),                               -- 专业
       Account VARCHAR(30) UNIQUE NOT NULL,   -- 账号
       Password CHAR(60) NOT NULL,   -- 密码
       role char(10) not null,					-- 角色
       create_time datetime default current_timestamp, -- 创建时间
       update_time datetime DEFAULT current_timestamp on update current_timestamp, -- 更新时间
       web_name varchar(20) null

);

-- 课程表 (Course)
CREATE TABLE if not exists Course (
      id char(19) PRIMARY KEY,         -- 课程ID，主键
      Hours INT NOT NULL,                              -- 课程学时
      Experiment_Hours INT NOT NULL,                   -- 实验学时
      Name VARCHAR(20) NOT NULL,               -- 课程名称
      Description TEXT                                 -- 课程描述
);

-- 教师课程表 (TeacherCourse)
CREATE TABLE if not exists TeacherCourse (
     Teacher_Account char(19) NOT NULL,                          -- 教师工号
     Course_Name varchar(25),
     Course_ID char(19) NOT NULL,                           -- 课程ID
     Semester VARCHAR(15) NOT NULL,                  -- 学期
     Course_Nums INT DEFAULT 0,                        -- 选课人数
     Current_Class VARCHAR(30),                              -- 上课班级
     Hours INT,                              -- 课程学时
     Experiment_Hours INT,                   -- 实验学时
     PRIMARY KEY (Teacher_Account, Course_ID),              -- 复合主键
     FOREIGN KEY (Teacher_Account) REFERENCES Teacher(Account),
     FOREIGN KEY (Course_ID) REFERENCES Course(id),
     INDEX (Teacher_Account)

);

-- 实验室负责人员表 (LabManage)
CREATE TABLE if not exists LabManage (
     id char(19) PRIMARY KEY,          -- 负责人员ID，主键
     Name VARCHAR(20) NOT NULL,                      -- 姓名
     Position VARCHAR(20) ,                            -- 职位
     Email VARCHAR(25) UNIQUE null,              -- 邮箱
     Phone VARCHAR(15) null                       -- 联系电话
);

-- 实验室表 (Laboratory)
CREATE TABLE if not exists Lab (
       id char(19) PRIMARY KEY,     -- 实验室ID，主键
       Name varchar(30) not null ,                          -- 实验室名称
       Staff_ID char(19) NOT NULL,                            -- 负责人ID
       Capacity INT NULL,                           -- 实验室容量
       Configuration TEXT,                              -- 实验室配置介绍
       Status ENUM('Available', 'NotAvailable', 'Maintenance') DEFAULT 'Available', -- 实验室状态
       FOREIGN KEY (Staff_ID) REFERENCES LabManage(id),
       INDEX (Capacity)
);


-- 预约表 (Reservation)
CREATE TABLE IF NOT EXISTS Reservation (
   id int PRIMARY KEY auto_increment,   -- 预约ID，主键
   Teacher_Account varchar(20) NOT NULL,        -- 教师ID
   Course_ID char(19) NOT NULL,                  -- 课程ID
   Lab_ID char(19) NOT NULL,                     -- 实验室ID
   Teacher_Name varchar(20),                     -- 教师姓名
   Course_Name varchar(25),                      -- 课程名称
   Temp_Reservation char(6) DEFAULT FALSE,       -- 是否为临时预约
   Reservation_Date datetime default current_timestamp, -- 预约创建日期
   Specifics JSON NOT NULL COMMENT '{"week", "period"}', -- 预约的周数星期及节次 (JSON 类型)
   Week varchar(5) NOT NULL,                     -- 预约的周次
   unique((cast(Specifics ->> '$.week' as char(5)) collate utf8mb4_bin),
       (cast(Specifics ->> '$.period' as char(8)) collate utf8mb4_bin),
           Week,Lab_ID),
   index(Teacher_Account),
   index(Course_ID)

);


