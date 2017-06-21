/*
 * Copyright (C) 2011-2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is auto-generated. DO NOT MODIFY!
 * The source Renderscript file: /home/labpi/Documentos/renan/projetos/toyApps/implementations/renderscript/auto/Filter/app/src/main/rs/br/edu/ufsj/dcomp/filter/Controller.rs
 */

package br.edu.ufsj.dcomp.filter;

import android.support.v8.renderscript.*;
import br.edu.ufsj.dcomp.filter.ControllerBitCode;

/**
 * @hide
 */
public class ScriptC_Controller extends ScriptC {
    private static final String __rs_resource_name = "controller";
    // Constructor
    public  ScriptC_Controller(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              ControllerBitCode.getBitCode32(),
              ControllerBitCode.getBitCode64());
        __ALLOCATION = Element.ALLOCATION(rs);
        __I32 = Element.I32(rs);
    }

    private Element __ALLOCATION;
    private Element __I32;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_I32;
    private final static int mExportVarIdx_PM_gInputFilter1 = 0;
    private Allocation mExportVar_PM_gInputFilter1;
    public synchronized void set_PM_gInputFilter1(Allocation v) {
        setVar(mExportVarIdx_PM_gInputFilter1, v);
        mExportVar_PM_gInputFilter1 = v;
    }

    public Allocation get_PM_gInputFilter1() {
        return mExportVar_PM_gInputFilter1;
    }

    public Script.FieldID getFieldID_PM_gInputFilter1() {
        return createFieldID(mExportVarIdx_PM_gInputFilter1, null);
    }

    private final static int mExportVarIdx_PM_gOutputFilter1 = 1;
    private Allocation mExportVar_PM_gOutputFilter1;
    public synchronized void set_PM_gOutputFilter1(Allocation v) {
        setVar(mExportVarIdx_PM_gOutputFilter1, v);
        mExportVar_PM_gOutputFilter1 = v;
    }

    public Allocation get_PM_gOutputFilter1() {
        return mExportVar_PM_gOutputFilter1;
    }

    public Script.FieldID getFieldID_PM_gOutputFilter1() {
        return createFieldID(mExportVarIdx_PM_gOutputFilter1, null);
    }

    private final static int mExportVarIdx_PM_gOutputXSizeFilter1_Allocation = 2;
    private Allocation mExportVar_PM_gOutputXSizeFilter1_Allocation;
    public synchronized void set_PM_gOutputXSizeFilter1_Allocation(Allocation v) {
        setVar(mExportVarIdx_PM_gOutputXSizeFilter1_Allocation, v);
        mExportVar_PM_gOutputXSizeFilter1_Allocation = v;
    }

    public Allocation get_PM_gOutputXSizeFilter1_Allocation() {
        return mExportVar_PM_gOutputXSizeFilter1_Allocation;
    }

    public Script.FieldID getFieldID_PM_gOutputXSizeFilter1_Allocation() {
        return createFieldID(mExportVarIdx_PM_gOutputXSizeFilter1_Allocation, null);
    }

    private final static int mExportVarIdx_PM_gOutputTileFilter1 = 3;
    private Allocation mExportVar_PM_gOutputTileFilter1;
    public synchronized void set_PM_gOutputTileFilter1(Allocation v) {
        setVar(mExportVarIdx_PM_gOutputTileFilter1, v);
        mExportVar_PM_gOutputTileFilter1 = v;
    }

    public Allocation get_PM_gOutputTileFilter1() {
        return mExportVar_PM_gOutputTileFilter1;
    }

    public Script.FieldID getFieldID_PM_gOutputTileFilter1() {
        return createFieldID(mExportVarIdx_PM_gOutputTileFilter1, null);
    }

    private final static int mExportVarIdx_PM_gOutputXSizeFilter1 = 4;
    private int mExportVar_PM_gOutputXSizeFilter1;
    public synchronized void set_PM_gOutputXSizeFilter1(int v) {
        setVar(mExportVarIdx_PM_gOutputXSizeFilter1, v);
        mExportVar_PM_gOutputXSizeFilter1 = v;
    }

    public int get_PM_gOutputXSizeFilter1() {
        return mExportVar_PM_gOutputXSizeFilter1;
    }

    public Script.FieldID getFieldID_PM_gOutputXSizeFilter1() {
        return createFieldID(mExportVarIdx_PM_gOutputXSizeFilter1, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_filter1_tile = 1;
    public Script.KernelID getKernelID_filter1_tile() {
        return createKernelID(mExportForEachIdx_filter1_tile, 59, null, null);
    }

    public void forEach_filter1_tile(Allocation ain, Allocation aout) {
        forEach_filter1_tile(ain, aout, null);
    }

    public void forEach_filter1_tile(Allocation ain, Allocation aout, Script.LaunchOptions sc) {
        // check ain
        if (!ain.getType().getElement().isCompatible(__I32)) {
            throw new RSRuntimeException("Type mismatch with I32!");
        }
        // check aout
        if (!aout.getType().getElement().isCompatible(__I32)) {
            throw new RSRuntimeException("Type mismatch with I32!");
        }
        Type t0, t1;        // Verify dimensions
        t0 = ain.getType();
        t1 = aout.getType();
        if ((t0.getCount() != t1.getCount()) ||
            (t0.getX() != t1.getX()) ||
            (t0.getY() != t1.getY()) ||
            (t0.getZ() != t1.getZ()) ||
            (t0.hasFaces()   != t1.hasFaces()) ||
            (t0.hasMipmaps() != t1.hasMipmaps())) {
            throw new RSRuntimeException("Dimension mismatch between parameters ain and aout!");
        }

        forEach(mExportForEachIdx_filter1_tile, ain, aout, null, sc);
    }

    private final static int mExportFuncIdx_filter1_setAllocationSize = 0;
    public Script.InvokeID getInvokeID_filter1_setAllocationSize() {
        return createInvokeID(mExportFuncIdx_filter1_setAllocationSize);
    }

    public void invoke_filter1_setAllocationSize() {
        invoke(mExportFuncIdx_filter1_setAllocationSize);
    }

    private final static int mExportFuncIdx_filter1 = 1;
    public Script.InvokeID getInvokeID_filter1() {
        return createInvokeID(mExportFuncIdx_filter1);
    }

    public void invoke_filter1() {
        invoke(mExportFuncIdx_filter1);
    }

}

