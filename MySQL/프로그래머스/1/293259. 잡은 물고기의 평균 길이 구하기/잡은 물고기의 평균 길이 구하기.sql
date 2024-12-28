-- 코드를 작성해주세요
SELECT ROUND(AVG(IFNULL(length, 10)), 2) AS AVERAGE_LENGTH FROM fish_info;