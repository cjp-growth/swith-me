package project.swithme.order.test.arch;

import static project.swithme.order.test.arch.ArchTestConst.TEST_PACKAGE;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;

public class TestExcludeConfiguration implements ImportOption {

    @Override
    public boolean includes(Location location) {
        return !location.contains(TEST_PACKAGE);
    }
}
