----------- SEQUENCES CREATION -----------
DROP SEQUENCE empidseq;

CREATE SEQUENCE empidseq MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 200 CACHE 20 NOORDER NOCYCLE NOPARTITION;

DROP SEQUENCE eventidseq;

CREATE SEQUENCE eventidseq MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 200 CACHE 20 NOORDER NOCYCLE NOPARTITION;

DROP SEQUENCE formidseq;

CREATE SEQUENCE formidseq MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 200 CACHE 20 NOORDER NOCYCLE NOPARTITION;

DROP SEQUENCE formstatusseq;

CREATE SEQUENCE formstatusseq MINVALUE - 100 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 0 CACHE 20 NOORDER NOCYCLE NOPARTITION;

DROP SEQUENCE formgrdformatseq;

CREATE SEQUENCE formgrdformatseq MINVALUE - 100 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 0 CACHE 20 NOORDER NOCYCLE NOPARTITION;


DROP SEQUENCE responseidseq;

CREATE SEQUENCE responseidseq MINVALUE - 100 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 0 CACHE 20 NOORDER NOCYCLE NOPARTITION;


----------- TABLE CREATION --------------

DROP TABLE empid;

CREATE TABLE empid (
    empid         NUMBER(7) PRIMARY KEY NOT NULL,
    empname       VARCHAR2(255) NOT NULL,
    empaddress    VARCHAR2(255) NULL,
    empphono      VARCHAR2(255) NULL,
    empemail      VARCHAR2(255) NOT NULL UNIQUE,
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
    formcurrappliedtime    VARCHAR(255),
    formeventdate          VARCHAR(255),
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
    resp_time    VARCHAR(255),
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

CREATE TABLE formid_eventattchmnt (
    formid            NUMBER(7),
    eventattchmntid   NUMBER(7)
);

DROP TABLE eventattchmnt;

CREATE TABLE eventattchmnt (
    eventattchmntid   NUMBER(7),
    eventattchmnt     BLOB
);

-- INSERT VALUES KNOWN FOR EVENT

INSERT INTO eventid VALUES (
    eventidseq.NEXTVAL,
    'University Courses',
    '80%'
);

INSERT INTO eventid VALUES (
    eventidseq.NEXTVAL,
    'Seminars',
    '60%'
);

INSERT INTO eventid VALUES (
    eventidseq.NEXTVAL,
    'Certification Preparation Classes',
    '75%'
);

INSERT INTO eventid VALUES (
    eventidseq.NEXTVAL,
    'Certification',
    '100%'
);

INSERT INTO eventid VALUES (
    eventidseq.NEXTVAL,
    'Technical Training',
    '90%'
);

INSERT INTO eventid VALUES (
    eventidseq.NEXTVAL,
    'Other',
    '30%'
);

--STATUS 0 : DENIED - The form has been denied and a reason has been provided.

INSERT INTO formstatuscode VALUES (
    formstatusseq.NEXTVAL,
    'Denied'
);

-- STATUS 1 : IN PROGRESS - Going through the approval proccess through the three approvals (Dir. Sup., Dept. Head, BenCo)

INSERT INTO formstatuscode VALUES (
    formstatusseq.NEXTVAL,
    'In Progress'
);

--STATUS 2 : PENDING - Waiting on final confirmation after approvals, such as the passing grade or presentation

INSERT INTO formstatuscode VALUES (
    formstatusseq.NEXTVAL,
    'Currently Pending'
);

--STATUS 3 : APPROVED - The form has been approved and the money is on the way!!

INSERT INTO formstatuscode VALUES (
    formstatusseq.NEXTVAL,
    'Approved'
);


---- INSERT THE DIFFERENT TYPES OF GRADE FORMATS
INSERT INTO FORMGRDFORMATID VALUES (
formgrdformatseq.nextval,
'100 - 0', '70'
);

INSERT INTO FORMGRDFORMATID VALUES (
formgrdformatseq.nextval,
'A - F', 'C'
);

INSERT INTO FORMGRDFORMATID VALUES (
formgrdformatseq.nextval,
'PASS / FAIL', 'PASS'
);
-- Create an Insert Employee stored procedure. An employee has an inital reimbursement balance of $1000. 

CREATE OR REPLACE PROCEDURE insertempid (
    empname       IN VARCHAR2,
    empaddress    IN VARCHAR2,
    empphono      IN VARCHAR2,
    empemail      IN VARCHAR2,
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


-- Create an Insert Form stored procedure. 

CREATE OR REPLACE PROCEDURE insertform (
    empid IN NUMBER
)
    AS
BEGIN
    INSERT INTO empid_formid VALUES (
        empid,
        formidseq.NEXTVAL
    );

    INSERT INTO formid VALUES (
        formidseq.CURRVAL,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    );

    COMMIT;
END;
/
---- Stored procedure to insert response
CREATE OR REPLACE PROCEDURE insertresponseid (
    formidval   IN VARCHAR2
)
    AS
BEGIN
    INSERT INTO formid_responseid VALUES (
        formidval,
        responseidseq.NEXTVAL
    );

    INSERT INTO responseid VALUES (
        responseidseq.CURRVAL,
        NULL,
        NULL,
        NULL,
        NULL
    );

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertresponse (
    responseid   IN VARCHAR2,
    senderid    IN NUMBER,
    receiverid  IN NUMBER,
    responses    IN VARCHAR2
)
    AS
BEGIN

    UPDATE responseid SET 
        responseid.sender = senderid,
        responseid.receiver = receiverid,
        responseid.resp_time = get_truetime,
        responseid.response = responses
    WHERE responseid.responseid = (select max(responseid) from responseid);

    COMMIT;
END;
/

---- Stored procedure to insert a new event ----

CREATE OR REPLACE PROCEDURE insertevent (
    covname   IN VARCHAR2,
    covperc   IN VARCHAR2
)
    AS
BEGIN
    INSERT INTO eventid VALUES (
        eventidseq.NEXTVAL,
        covname,
        covperc
    );

    COMMIT;
END;
/

---- STORED PROCEDURE TO UPDATE FORM VALUES ----
CREATE OR REPLACE PROCEDURE updatenewform (
    formidvalue     IN NUMBER,
    newevent        IN NUMBER,
    eventdate       IN VARCHAR,
    eventlocation   IN VARCHAR,
    eventdescrip    IN VARCHAR,
    grdformatid     IN NUMBER,
    askingamount    IN NUMBER,
    urgentstatus    IN NUMBER
) AS
BEGIN UPDATE formid
SET
    formid.eventid = newevent,
    formid.formcurrappliedtime = gettime(),
    formid.formeventdate = eventdate,
    formid.formeventlocation = eventlocation,
    formid.formeventdescription = eventdescrip,
    formid.formgrdformatid = grdformatid,
    formid.urgencystatus = urgentstatus,
    formid.FORMREIMBAMT = askingamount
WHERE
    formid.formid = formidvalue;

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE updatenewformfull (
    formidval       IN NUMBER,
    newevent        IN NUMBER,
    currentdate     IN VARCHAR,
    eventdate       IN VARCHAR,
    eventlocation   IN VARCHAR,
    eventdescrip    IN VARCHAR,
    wrj             IN BLOB,
    grdformatid     IN NUMBER,
    formstatus      IN NUMBER,
    askingamount    IN NUMBER,
    amountstatus    IN NUMBER,
    isurgent        IN NUMBER,
    isheadapproval  IN NUMBER,
    issuperapprv    IN NUMBER,
    isbencoapprv    IN NUMBER
) AS
BEGIN UPDATE formid
SET
    formid.eventid = newevent,
    formid.formcurrappliedtime = currentdate,
    formid.formeventdate = eventdate,
    formid.formeventlocation = eventlocation,
    formid.formeventdescription = eventdescrip,
    formid.formwrj = wrj,
    formid.formgrdformatid = grdformatid,
    formid.formstatusid = formstatus,
    formid.urgencystatus = isurgent,
    formid.FORMREIMBAMT = askingamount,
    formid.excfundsstatus = amountstatus,
    formid.deptheadapprv = isheadapproval,
    formid.dirsuperapprv = issuperapprv,
    formid.bencoapprv = isbencoapprv
WHERE
    formid.formid = formidval;

    COMMIT;
END;
/



---- FUNCTION TO GET CURRENT DAY (EVEN THOUGH THE NAME OF THE FUNCTION IS GETTIME ----

CREATE OR REPLACE FUNCTION gettime RETURN VARCHAR2 AS
    get_time   VARCHAR(260);
BEGIN
    SELECT
        TO_CHAR(LOCALTIMESTAMP,'YYYY-MM-DD')
    INTO get_time
    FROM
        dual;

    RETURN get_time;
END;
/

----- FUNCTION TO GET CURRENT DAY AND TIME -----
CREATE OR REPLACE FUNCTION get_truetime RETURN VARCHAR2 AS
    get_time    VARCHAR(260);
BEGIN
    SELECT
        TO_CHAR(LOCALTIMESTAMP, 'YYYY-MM-DD HH24:MI:SS')
        INTO get_time
        FROM
            dual;
            
        RETURN get_time;
END;
/

---- INSERT INTO EMPLOYEE ----
EXECUTE insertempid('Timmy Jones','11623 Apples Lane Appler, GA 36815','(706)123-1234','jones123@sleeps.com','12345',200,200,202,1,1,0);

EXECUTE insertempid('Emily Smith','12123 Apples Lane Appler, GA 71353','(706)123-1235','emily123@sleeps.com','12345',200,201,202,0,1,0);

EXECUTE insertempid('Mary Jane','26123 Apples Lane Appler, GA 11115','(706)123-1236','mary123@sleeps.com','12345',200,201,202,0,0,1);

EXECUTE insertempid('Nathan Austin','12743 Apples Lane Appler, GA 32725','(706)123-1237','nathan123@sleeps.com','12345',200,201,202,0,0,0);

EXECUTE insertempid('Michael Joe','52253 Apples Lane Appler, GA 30835','(706)123-1238','michael23@sleeps.com','12345',200,200,202,0,0,0);

EXECUTE insertempid('Sunny Pie','12314 Apples Lane Appler, GA 30815','(706)123-1239','sunny123@sleeps.com','12345',200,200,202,0,0,0);

EXECUTE insertempid('Bobby Will','52123 Apples Lane Appler, GA 37815','(706)123-1228','bobby123@sleeps.com','12345',200,200,202,0,0,0);

EXECUTE insertempid('Marcus Lots','62123 Apples Lane Appler, GA 35815','(706)123-1138','marcus123@sleeps.com','12345',200,200,202,0,0,0);

EXECUTE insertempid('Corwin Wins','16533 Apples Lane Appler, GA 37415','(706)123-1638','corwin111@sleeps.com','12345',200,200,202,0,0,0);

EXECUTE insertempid('Nick Low','16123 Apples Lane Appler, GA 39815','(706)123-1288','nicklow@sleeps.com','12345',200,200,202,0,0,0);

--- INSERT FORM CREATES ENTRIES INTO THE EMPID_FORMID

EXECUTE insertform(201);
EXECUTE updatenewform(formidseq.CURRVAL,201, '2018-07-01', '1231 Apple Lane, Tampa, Fl 33333','The seminar will cover American Sign Language and its benefits in teaching.', 0, 300.00, 0);

EXECUTE insertform(201);
EXECUTE updatenewform(formidseq.CURRVAL,202, '2018-07-10', '1231 Apple Lane, Tampa, Fl 33333','The certification preparation class on American Sign Language will prepare the students to become fluent in American Sign Language.', 0, 325.00, 0);

EXECUTE insertform(204);
EXECUTE updatenewform(formidseq.CURRVAL,202, '2018-07-11', '1231 Apple Lane, Tampa, Fl 33333','The class will aid teacher assistants like me to be a teacher.', 0, 315.20, 0);

EXECUTE insertform(204);
EXECUTE updatenewform(formidseq.CURRVAL,203, '2018-07-19', '1231 Apple Lane, Tampa, Fl 33333','Received certification on First Aid and CPR.', 0, 151.00, 0);

EXECUTE insertform(205);
EXECUTE updatenewform(formidseq.CURRVAL,205, '2018-07-23', '1231 Apple Lane, Tampa, Fl 33333','Requesting reimbursement for a new printer.', 0, 197.52, 0);


--- INSERT RESPONSE ENTRIES INTO THE FORMID_RESPONSEID

EXECUTE insertresponseid(200);
EXECUTE insertresponse(responseidseq.currval, 201, 200, 'Good morning Mr. Jones, I am requesting for your approval on my form# 200.');
EXECUTE insertresponseid(200);
EXECUTE insertresponse(responseidseq.currval, 200, 201, 'Good morning Mrs. Smith. I will need more information on the seminar.');

EXECUTE insertresponseid(200);
EXECUTE insertresponse(responseidseq.currval, 201, 200, 'Good morning Mr. Jones. The seminar is designed to engage participants and their critical thinking skills.');

