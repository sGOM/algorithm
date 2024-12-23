-- 코드를 입력하세요
WITH U AS (
    SELECT *
    FROM USER_INFO
    WHERE JOINED BETWEEN '2021-01-01' AND '2021-12-31'
),
O AS (
    SELECT *
    FROM ONLINE_SALE
)

SELECT
    YEAR(SALES_DATE) AS YEAR,
    MONTH(SALES_DATE) AS MONTH,
    COUNT(DISTINCT O.USER_ID) AS PURCHASED_USERS,
    ROUND(COUNT(DISTINCT O.USER_ID) / (SELECT COUNT(*) FROM U), 1) AS PUCHASED_RATIO
FROM U
JOIN O ON U.USER_ID = O.USER_ID
GROUP BY YEAR, MONTH
ORDER BY YEAR, MONTH
