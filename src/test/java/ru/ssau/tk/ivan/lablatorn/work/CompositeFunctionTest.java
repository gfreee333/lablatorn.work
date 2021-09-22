package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
public final static double DELTA = 0.0001;
    @Test
    public void testApply() {
        MathFunction identity = new IdentityFunction();
        MathFunction root = new ThreeRootFunction();
        MathFunction identityRoot = new CompositeFunction(identity,root);
        MathFunction rootIdentity = new CompositeFunction(root, identity);
        assertEquals(identity.apply(22.2),22.2);
        assertEquals(rootIdentity.apply(27),3, DELTA);
        assertEquals(identityRoot.apply(4000),15.8740,DELTA);
        assertEquals(rootIdentity.apply(200),5.8480,DELTA);
    }
}