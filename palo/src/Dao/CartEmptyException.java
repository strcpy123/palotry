package Dao;
import java.lang.Exception;
@SuppressWarnings("serial")
public class CartEmptyException extends Exception{
	public CartEmptyException(String c){
		super(c);
		
	}
}
