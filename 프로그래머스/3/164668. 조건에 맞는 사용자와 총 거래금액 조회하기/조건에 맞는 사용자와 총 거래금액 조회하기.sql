-- 코드를 입력하세요
SELECT user_id, nickname, sum(A.price) as TOTAL_SALES
from used_goods_board as A 
join used_goods_user as B
on A.writer_id = B.user_id
where status = "DONE"
group by user_id
having TOTAL_SALES >= 700000
order by TOTAL_SALES