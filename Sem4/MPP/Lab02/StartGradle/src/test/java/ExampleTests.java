import model.ComputerRepairRequest;
import org.junit.Assert;
import org.junit.Test;

public class ExampleTests {
    @Test
    public void testExample() {
        ComputerRepairRequest crr = new ComputerRepairRequest();
        Assert.assertEquals("", crr.getOwnerName());
        Assert.assertEquals("", crr.getOwnerAddress());
    }
}
