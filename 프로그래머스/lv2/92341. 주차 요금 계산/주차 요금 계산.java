import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Car> map = new TreeMap<>();
        
        for(String record : records) {
            String[] s = record.split("\\s");
            
            if(!map.containsKey(s[1])) {
                map.put(s[1], new Car(s[1]));
            }
            
            if(s[2].equals("IN")) {
                map.get(s[1]).setInTime(s[0]);
            } else {
                map.get(s[1]).setOutTime(s[0]);
            }
        }
        
        int[] answer = new int[map.size()];
        int idx = 0;
        
        // 아직 출차를 안한 차들이 있다면 요금 계산
        for(Car car : map.values()) {
            if(car.isPark) {
                car.setOutTime("23:59");
            }
            
            // 모든 차 요금계산
            car.calc(fees);
            
            answer[idx++] = car.fee;
        }
        
        return answer;
    }
}

class Car {
    String carNo;
    int inTime;
    int outTime;
    int time;
    int fee;
    boolean isPark;
    
    Car(String carNo) {
        this.carNo = carNo;
        fee = 0;
    }
    
    public void setInTime(String inTime) {
        String[] s = inTime.split(":");
        this.inTime = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
        isPark = true;
    }
    
    public void setOutTime(String outTime) {
        String[] s = outTime.split(":");
        this.outTime = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
        
        // 차가 주차한 누적시간 더하기
        time += this.outTime - this.inTime;
        isPark = false;
    }
    
    public void calc(int[] fees) {        
        // System.out.println(time);
        
        if(time > fees[0]) {
            fee += fees[3] * ((time - fees[0] + fees[2] - 1) / fees[2]);
        }
        
        fee += fees[1];
    }
    
}