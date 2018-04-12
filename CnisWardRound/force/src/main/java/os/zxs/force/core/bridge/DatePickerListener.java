package os.zxs.force.core.bridge;

import os.zxs.force.core.view.DatePickerView;

public class DatePickerListener {
	public interface DateListener {

		public String getDate();
		public void setDate(String date);
		public DatePickerView getDatePickerView();

	}

	public interface DateChangeListener {

		public void notityDateChange(DatePickerView datePickerView);

	}
}
