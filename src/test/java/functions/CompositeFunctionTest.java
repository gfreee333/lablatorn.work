package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
public final static double DELTE = 0.0001;
    @Test // для того, чтобы завершить тесты необходима еще одна функция от преподователя
    public void testApply() {
        MathFunction identity = new IdentityFunction();
        MathFunction root = new ThreeRootFunction();
        MathFunction identityRoot = new CompositeFunction(identity,root);
        MathFunction rootIdentity = new CompositeFunction(root, identity);
        assertEquals(identity.apply(22.2),22.2);
        assertEquals(rootIdentity.apply(27),3, DELTE);
        assertEquals(identityRoot.apply(4000),15.8740,DELTE);
        assertEquals(rootIdentity.apply(200),5.8480,DELTE);
    }
}