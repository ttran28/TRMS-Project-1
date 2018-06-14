----------- SEQUENCES -----------
DROP SEQUENCE empidseq;

CREATE SEQUENCE empidseq MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 200 CACHE 20 NOORDER NOCYCLE NOPARTITION;

DROP TABLE empid;

CREATE TABLE empid (
    empid         NUMBER(7) PRIMARY KEY NOT NULL,
    empname       VARCHAR2(255) NOT NULL,
    empaddress    VARCHAR2(255) NULL,
    empphono      VARCHAR2(255) NULL,
    empemail      VARCHAR2(255) NULL,
    empusername   VARCHAR2(255) UNIQUE NOT NULL,
    emppassword   VARCHAR2(255) NOT NULL,
    empreimbal    NUMBER(19,4),
    dirsupid      NUMBER(7) NULL,
    deptheadid    NUMBER(7) NULL,
    bencoid       NUMBER(7),
    isdirsup      NUMBER(1),
    isdepthead    NUMBER(1),
    isbenco       NUMBER(1)
);

DROP TABLE empid_formid;

CREATE TABLE empid_formid (
    empid    NUMBER(7) NOT NULL,
    formid   NUMBER(7) PRIMARY KEY NOT NULL
);

DROP TABLE formid;

CREATE TABLE formid (
    formid                 NUMBER(7),
    eventid                NUMBER(7),
    formcurrappliedtime    DATE,
    formeventdate          DATE,
    formeventlocation      VARCHAR(255),
    formeventdescription   VARCHAR(255),
    formwrj                BLOB,
    formpresgrade          VARCHAR(255),
    formgrdformatid        NUMBER(3),
    formstatusid           NUMBER(3),
    formreimbamt           NUMBER(19,4),
    excfundsstatus         NUMBER(3),
    urgencystatus          NUMBER(1),
    deptheadapprv          NUMBER(2),
    dirsuperapprv          NUMBER(2),
    bencoapprv             NUMBER(2)
);

DROP TABLE eventid;

CREATE TABLE eventid (
    eventid           NUMBER(7),
    coveragename      VARCHAR(255),
    coveragepercent   VARCHAR(255)
);

DROP TABLE formgrdformatid;

CREATE TABLE formgrdformatid (
    formgrdformatid   NUMBER(3),
    formgrdformat     VARCHAR(255),
    formgrdcutoff     VARCHAR(255)
);

DROP TABLE formstatuscode;

CREATE TABLE formstatuscode (
    formstatuscode   NUMBER(3),
    formstatus       VARCHAR(255)
);

DROP TABLE formid_responseid;

CREATE TABLE formid_responseid (
    formid       NUMBER(7),
    responseid   NUMBER(7)
);

DROP TABLE responseid;

CREATE TABLE responseid (
    responseid   NUMBER(7),
    sender       NUMBER(7),
    receiver     NUMBER(7),
    resp_time    DATE,
    response     VARCHAR(255)
);

DROP TABLE formid_apprvattchmntid;

CREATE TABLE formid_apprvattchmntid (
    formid               NUMBER(7),
    approvalattchmntid   NUMBER(7)
);

DROP TABLE apprvattchmentid;

CREATE TABLE apprvattchmentid (
    formid            NUMBER(7),
    apprvalattchmnt   BLOB
);

DROP TABLE formid_eventattchmnt;

CREATE TABLE formid_eventattchment (
    formid            NUMBER(7),
    eventattchmntid   NUMBER(7)
);

DROP TABLE eventattchmnt;

CREATE TABLE eventattchmnt (
    eventattchmntid   NUMBER(7),
    eventattchmnt     BLOB
);
-- Create an Insert Employee stored procedure

DROP PROCEDURE insertempid;

CREATE OR REPLACE PROCEDURE insertempid (
    empname       IN VARCHAR2,
    empaddress    IN VARCHAR2,
    empphono      IN VARCHAR2,
    empemail      IN VARCHAR2,
    empusername   IN VARCHAR2,
    emppassword   IN VARCHAR2,
    dirsupid      IN NUMBER,
    deptheadid    IN NUMBER,
    bencoid       IN NUMBER,
    isdirsup      IN NUMBER,
    isdepthead    IN NUMBER,
    isbenco       IN NUMBER
)
    AS
BEGIN
    INSERT INTO empid VALUES (
        empidseq.NEXTVAL,
        empname,
        empaddress,
        empphono,
        empemail,
        empusername,
        emppassword,
        1000.00,
        dirsupid,
        deptheadid,
        bencoid,
        isdirsup,
        isdepthead,
        isbenco
    );

    COMMIT;
END;
/

EXECUTE insertempid('Timmy Jones','123 Apples Lane Appler, GA 30815','(706)123-1234','jones123@gmail.com','jones123','12345',1,1,0,1,1,0);

SELECT
    *
FROM
    empid;