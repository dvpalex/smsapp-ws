package apptest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Apptest {

	public static void main(String[] args) {
		
		Apptest app = new Apptest();
		
		Date begin = DateUtils.getDateWithTimeOnly(9, 0, 0);
		Date end = DateUtils.getDateWithTimeOnly(18, 0, 0);
		
		for(Date dt :app.cronExpression(begin, end, 90)){
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dt);
			
			System.out.println(String.format("0 %s %s * * *", calendar.get(Calendar.MINUTE), calendar.get(Calendar.HOUR_OF_DAY)));
		}
		
		

	}

	
	public List<Date> cronExpression( Date begin , Date end,int minutes){
		
		long mBegin = DateUtils.intervalMilliseconds(begin);
		long mEnd = DateUtils.intervalMilliseconds(end);
		long mMinutes = minutes * 60 * 1000;
		
		long qt = (mEnd-mBegin)/mMinutes;
		long pos = mBegin;
		List<Date> lst = new ArrayList<Date>();
		
		
		if(minutes < 60){
			lst.add(DateUtils.getDateWithTimeOnly(0, minutes, 0));
		}else{
		
			for(int i = 0; i < qt; i++){
				
				if((pos + mMinutes) < mEnd){
					pos += mMinutes;
					lst.add(DateUtils.intervalDate(pos));
				}
			}
		
		}
		System.out.println(lst);
		
		return lst;
			
		}
	
		
	
}
