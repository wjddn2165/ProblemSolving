-- 코드를 입력하세요
SELECT price - (price % 10000) as PRICE_GROUP, count(*) as PRODUCTS
from product
group by PRICE_GROUP
order by PRICE_GROUP