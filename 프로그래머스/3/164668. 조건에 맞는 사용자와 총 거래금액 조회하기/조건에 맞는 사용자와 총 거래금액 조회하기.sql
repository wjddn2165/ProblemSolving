-- 코드를 입력하세요
SELECT B.user_id, B.nickname, sum(A.price) as TOTAL_SALES
from used_goods_board as A 
join used_goods_user as B
on (A.writer_id = B.user_id AND status = "DONE")
group by A.writer_id
having sum(A.price) >= 700000
order by TOTAL_SALES