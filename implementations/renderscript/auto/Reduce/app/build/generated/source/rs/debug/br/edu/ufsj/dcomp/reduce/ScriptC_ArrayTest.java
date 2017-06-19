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
 * The source Renderscript file: /home/labpi/Documentos/renan/projetos/toyApps/implementations/renderscript/auto/Reduce/app/src/main/rs/br/edu/ufsj/dcomp/reduce/ArrayTest.rs
 */

package br.edu.ufsj.dcomp.reduce;

import android.support.v8.renderscript.*;
import br.edu.ufsj.dcomp.reduce.ArrayTestBitCode;

/**
 * @hide
 */
public class ScriptC_ArrayTest extends ScriptC {
    private static final String __rs_resource_name = "arraytest";
    // Constructor
    public  ScriptC_ArrayTest(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              ArrayTestBitCode.getBitCode32(),
              ArrayTestBitCode.getBitCode64());
        __ALLOCATION = Element.ALLOCATION(rs);
        __I32 = Element.I32(rs);
    }

    private Element __ALLOCATION;
    private Element __I32;
    private FieldPacker __rs_fp_ALLOCATION;
    private final static int mExportVarIdx_PM_gInputReduce1 = 0;
    private Allocation mExportVar_PM_gInputReduce1;
    public synchronized void set_PM_gInputReduce1(Allocation v) {
        setVar(mExportVarIdx_PM_gInputReduce1, v);
        mExportVar_PM_gInputReduce1 = v;
    }

    public Allocation get_PM_gInputReduce1() {
        return mExportVar_PM_gInputReduce1;
    }

    public Script.FieldID getFieldID_PM_gInputReduce1() {
        return createFieldID(mExportVarIdx_PM_gInputReduce1, null);
    }

    private final static int mExportVarIdx_PM_gTileReduce1 = 1;
    private Allocation mExportVar_PM_gTileReduce1;
    public synchronized void set_PM_gTileReduce1(Allocation v) {
        setVar(mExportVarIdx_PM_gTileReduce1, v);
        mExportVar_PM_gTileReduce1 = v;
    }

    public Allocation get_PM_gTileReduce1() {
        return mExportVar_PM_gTileReduce1;
    }

    public Script.FieldID getFieldID_PM_gTileReduce1() {
        return createFieldID(mExportVarIdx_PM_gTileReduce1, null);
    }

    private final static int mExportVarIdx_PM_gOutputResultadoReduce1 = 2;
    private Allocation mExportVar_PM_gOutputResultadoReduce1;
    public synchronized void set_PM_gOutputResultadoReduce1(Allocation v) {
        setVar(mExportVarIdx_PM_gOutputResultadoReduce1, v);
        mExportVar_PM_gOutputResultadoReduce1 = v;
    }

    public Allocation get_PM_gOutputResultadoReduce1() {
        return mExportVar_PM_gOutputResultadoReduce1;
    }

    public Script.FieldID getFieldID_PM_gOutputResultadoReduce1() {
        return createFieldID(mExportVarIdx_PM_gOutputResultadoReduce1, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_reduce1_tile = 1;
    public Script.KernelID getKernelID_reduce1_tile() {
        return createKernelID(mExportForEachIdx_reduce1_tile, 42, null, null);
    }

    public void forEach_reduce1_tile(Allocation aout) {
        forEach_reduce1_tile(aout, null);
    }

    public void forEach_reduce1_tile(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__I32)) {
            throw new RSRuntimeException("Type mismatch with I32!");
        }
        forEach(mExportForEachIdx_reduce1_tile, (Allocation) null, aout, null, sc);
    }

    private final static int mExportFuncIdx_reduce1 = 0;
    public Script.InvokeID getInvokeID_reduce1() {
        return createInvokeID(mExportFuncIdx_reduce1);
    }

    public void invoke_reduce1() {
        invoke(mExportFuncIdx_reduce1);
    }

}

