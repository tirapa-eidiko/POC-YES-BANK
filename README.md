# POC-YES-BANK
This is a ACE project for OpenShift POC

--ORACLE PROCEDURE CREATION---

CREATE OR REPLACE
PROCEDURE GET_ADR_DTLS (ano IN  VARCHAR,
                      cursor OUT SYS_REFCURSOR) AS 
BEGIN 
    OPEN cursor FOR
    SELECT TXNID AS IFSC,
           CHANNELID AS COD_ACCT_NO,
           USERID AS COD_AADHAAR_NO,AADHAAR_NO AS COD_CUST_ID
    FROM   PROC_TXN_STATUS1
    WHERE  AADHAAR_NO = ano;
    INSERT INTO PROC_TXN_STATUS1 VALUES ( ano,'YBR',ano,ano );
END GET_ADR_DTLS;

==================================================================
------ESQL PROCEDURE CREATION

    
    CREATE PROCEDURE  GET_ADR_DTLS(IN AADHAAR_NO CHARACTER )
	LANGUAGE DATABASE
	DYNAMIC RESULT SETS 1
	EXTERNAL NAME "GET_ADR_DTLS";
========================================================================
------ESQL PROCEDURE EXECUTION
CALL GET_ADR_DTLS(adhharNo,Environment.Variables.AadhaarLinkedDtls[]);


CREATE OR REPLACE
PROCEDURE PROC_TXN_STATUSS (ano IN  VARCHAR,txnId IN  VARCHAR,channelId IN  VARCHAR,
                      cursor OUT SYS_REFCURSOR) AS 
BEGIN 
    OPEN cursor FOR
    SELECT TXNID AS IFSC,
           CHANNELID AS COD_ACCT_NO,
           USERID AS COD_AADHAAR_NO,AADHAAR_NO AS COD_CUST_ID
    FROM   PROC_TXN_STATUS1
    WHERE  AADHAAR_NO = ano AND TXNID = txnId AND CHANNELID = channelId ;
    INSERT INTO PROC_TXN_STATUS1(AADHAAR_NO,CHANNELID,USERID,TXNID) VALUES ( ano,channelId,ano,txnId );
END PROC_TXN_STATUSS;;
