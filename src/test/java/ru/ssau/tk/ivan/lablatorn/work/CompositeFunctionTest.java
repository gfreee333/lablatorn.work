package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    public final static double DELTA = 0.0001;
    private  final MathFunction identity = new IdentityFunction();
    private final MathFunction root = new ThreeRootFunction();
    private final MathFunction identityRoot = new CompositeFunction(identity, root);
    private final MathFunction rootIdentity = new CompositeFunction(root, identity);

    @Test
    public void testApply() {
        assertEquals(identity.apply(22.2), 22.2);
        assertEquals(rootIdentity.apply(27), 3, DELTA);
        assertEquals(identityRoot.apply(4000), 15.8740, DELTA);
        assertEquals(rootIdentity.apply(200), 5.8480, DELTA);
    }
}