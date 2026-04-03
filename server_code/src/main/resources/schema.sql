-- H2 Database Schema for Hospital Management System
-- Simplified version for development/testing

-- Token table
CREATE TABLE IF NOT EXISTS token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid BIGINT NOT NULL,
    username VARCHAR(200),
    tablename VARCHAR(200),
    role VARCHAR(200),
    token VARCHAR(500),
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiratedtime TIMESTAMP
);

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    username VARCHAR(200) NOT NULL,
    password VARCHAR(200),
    role VARCHAR(200),
    token VARCHAR(500)
);

-- Yonghu (Patient) table
CREATE TABLE IF NOT EXISTS yonghu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    zhanghao VARCHAR(200),
    mima VARCHAR(200),
    xingming VARCHAR(200),
    xingbie VARCHAR(200),
    shouji VARCHAR(200),
    touxiang LONGTEXT,
    money DOUBLE DEFAULT 0
);

-- Yisheng (Doctor) table
CREATE TABLE IF NOT EXISTS yisheng (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    yishengzhanghao VARCHAR(200),
    mima VARCHAR(200),
    yishengxingming VARCHAR(200),
    xingbie VARCHAR(200),
    dianhua VARCHAR(200),
    touxiang LONGTEXT,
    keshi VARCHAR(200),
    zhicheng VARCHAR(200),
    money DOUBLE DEFAULT 0
);

-- Yishengyuyue (Doctor Appointment) table
CREATE TABLE IF NOT EXISTS yishengyuyue (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    yuyuebianhao VARCHAR(200),
    yishengzhanghao VARCHAR(200),
    dianhua VARCHAR(200),
    yuyueshijian TIMESTAMP,
    zhanghao VARCHAR(200),
    shouji VARCHAR(200),
    beizhu VARCHAR(200),
    sfsh VARCHAR(200) DEFAULT '否',
    shhf LONGTEXT
);

-- Jiuzhentongzhi (Visit Notification) table - Updated with notification status
CREATE TABLE IF NOT EXISTS jiuzhentongzhi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tongzhibianhao VARCHAR(200),
    yishengzhanghao VARCHAR(200),
    dianhua VARCHAR(200),
    jiuzhenshijian TIMESTAMP,
    tongzhishijian TIMESTAMP,
    zhanghao VARCHAR(200),
    shouji VARCHAR(200),
    tongzhibeizhu VARCHAR(200),
    tongzhizhuangtai INT DEFAULT 0
);

-- Paibanxinxi (Schedule Info) table
CREATE TABLE IF NOT EXISTS paibanxinxi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    yishengzhanghao VARCHAR(200),
    yishengxingming VARCHAR(200),
    xingbie VARCHAR(200),
    keshi VARCHAR(200),
    zhicheng VARCHAR(200),
    paibanshijian VARCHAR(200),
    yuyueshijian VARCHAR(200),
    dianhua VARCHAR(200)
);

-- Zhenduanbingli (Diagnosis Record) table
CREATE TABLE IF NOT EXISTS zhenduanbingli (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    zhenduanbianhao VARCHAR(200),
    zhanghao VARCHAR(200),
    shouji VARCHAR(200),
    zhenduanneirong LONGTEXT,
    zhenduanjieguo LONGTEXT,
    zhenduanshijian TIMESTAMP,
    yishengzhanghao VARCHAR(200),
    dianhua VARCHAR(200)
);

-- Chufangxinxi (Prescription Info) table
CREATE TABLE IF NOT EXISTS chufangxinxi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    yaopinmingcheng VARCHAR(200),
    danjia DOUBLE,
    kaifangshuliang INT,
    fuyongfangfa VARCHAR(200),
    qitafeiyong DOUBLE,
    xujiaojine DOUBLE,
    zhanghao VARCHAR(200),
    shouji VARCHAR(200),
    kaifangshijian TIMESTAMP,
    yishengzhanghao VARCHAR(200),
    dianhua VARCHAR(200),
    qita VARCHAR(200),
    ispay VARCHAR(200) DEFAULT '未支付'
);

-- Yaopinxinxi (Medicine Info) table
CREATE TABLE IF NOT EXISTS yaopinxinxi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    yaopinmingcheng VARCHAR(200),
    yaopinleixing VARCHAR(200),
    yaopintupian LONGTEXT,
    danjia DOUBLE,
    kucun INT,
    jinhuoshijian TIMESTAMP,
    yaopinxiangqing LONGTEXT
);

-- Rukujilu (Inbound Record) table
CREATE TABLE IF NOT EXISTS rukujilu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    yaopinmingcheng VARCHAR(200),
    kucun INT,
    rukushijian TIMESTAMP,
    rukubeizhu VARCHAR(200)
);

-- Chukujilu (Outbound Record) table
CREATE TABLE IF NOT EXISTS chukujilu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    yaopinmingcheng VARCHAR(200),
    kucun INT,
    chukushijian TIMESTAMP,
    chukubeizhu VARCHAR(200)
);

-- Jiuzhenqiandao (Visit Check-in) table
CREATE TABLE IF NOT EXISTS jiuzhenqiandao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    jiuzhenbianhao VARCHAR(200),
    yishengzhanghao VARCHAR(200),
    dianhua VARCHAR(200),
    jiuzhenshijian TIMESTAMP,
    shouji VARCHAR(200),
    zhanghao VARCHAR(200),
    beizhu VARCHAR(200)
);

-- News table
CREATE TABLE IF NOT EXISTS news (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    title VARCHAR(200),
    introduction LONGTEXT,
    picture LONGTEXT,
    content LONGTEXT
);

-- Config table
CREATE TABLE IF NOT EXISTS config (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    name VARCHAR(200),
    value LONGTEXT,
    url LONGTEXT
);

-- Friendship Link table
CREATE TABLE IF NOT EXISTS friendship_link (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    link_name VARCHAR(200),
    link_image LONGTEXT,
    link_url LONGTEXT
);

-- Messages table
CREATE TABLE IF NOT EXISTS messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    userid BIGINT,
    username VARCHAR(200),
    avatarurl LONGTEXT,
    content LONGTEXT,
    cpicture LONGTEXT,
    reply LONGTEXT,
    rpicture LONGTEXT
);

-- Chat Friend table
CREATE TABLE IF NOT EXISTS chat_friend (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    uid BIGINT NOT NULL,
    fid BIGINT NOT NULL,
    name VARCHAR(200),
    picture LONGTEXT,
    role VARCHAR(200),
    tablename VARCHAR(200),
    alias VARCHAR(200),
    type INT DEFAULT 0
);

-- Chat Message table
CREATE TABLE IF NOT EXISTS chat_message (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    uid BIGINT NOT NULL,
    fid BIGINT NOT NULL,
    content VARCHAR(200),
    format INT,
    is_read INT DEFAULT 0
);

-- Syslog table
CREATE TABLE IF NOT EXISTS syslog (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    username VARCHAR(200),
    operation VARCHAR(200),
    method VARCHAR(200),
    params LONGTEXT,
    time BIGINT,
    ip VARCHAR(200)
);

-- Menu table
CREATE TABLE IF NOT EXISTS menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    addtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    menujson LONGTEXT
);

-- Insert default admin user
INSERT INTO users (id, username, password, role) VALUES (1, 'admin', 'admin', '管理员');
