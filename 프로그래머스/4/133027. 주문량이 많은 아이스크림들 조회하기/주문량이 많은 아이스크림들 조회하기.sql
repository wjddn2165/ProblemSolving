-- 코드를 입력하세요
select f.flavor
from first_half f right outer join july j
on f.shipment_id = j.shipment_id
group by j.flavor
order by (f.total_order + sum(j.total_order)) DESC
limit 3