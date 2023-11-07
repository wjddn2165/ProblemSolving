-- 코드를 입력하세요
SELECT ingredient_type, sum(total_order) as TOTAL_ORDER
from first_half join icecream_info
using (FLAVOR)
group by ingredient_type
order by TOTAL_ORDER