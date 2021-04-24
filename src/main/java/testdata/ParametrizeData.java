package testdata;

import org.testng.annotations.DataProvider;

public class ParametrizeData {
@DataProvider(name="value")
	public Object[][] data()
	{
		Object[][]dataValue=new Object[][]
				{
			{"Argentina","SOUMYA"},{"Australia","KUNU"}
				};
				
				return dataValue;
		
	}
}
