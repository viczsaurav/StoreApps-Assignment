import static org.junit.Assert.*;

import org.junit.Test;

import com.iss.storeApplication.dao.CommonDao;
import com.iss.storeApplication.domain.Product;


public class saverecordTest {

	@Test
	public void test() {
		
	    Product p1 = new Product();
	    p1.setBarCode(1234L);
	    p1.setDescription("Hello");
	    p1.setProductId("1234");
	    p1.setOrderQty(12345);
	    p1.setPrice(102.4);
	    p1.setQtyAvailable(12);
	    p1.setReorderQty(123);
	    
	    assertTrue(CommonDao.save(p1,new Global().getProductFilepath()));
		
	}

}
