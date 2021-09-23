package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    public final static double DELTA = 0.0001;
    private MathFunction identity = new IdentityFunction();
    private MathFunction root = new ThreeRootFunction();
    private MathFunction identityRoot = new CompositeFunction(identity, root);
    private MathFunction rootIdentity = new CompositeFunction(root, identity);

    @Test
    public void testApply() {
        assertEquals(identity.apply(22.2), 22.2);
        assertEquals(rootIdentity.apply(27), 3, DELTA);
        assertEquals(identityRoot.apply(4000), 15.8740, DELTA);
        assertEquals(rootIdentity.apply(200), 5.8480, DELTA);
    }
}