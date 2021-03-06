package framework.jogl.postprocessingfilters;

/**
 **   __ __|_  ___________________________________________________________________________  ___|__ __
 **  //    /\                                           _                                  /\    \\  
 ** //____/  \__     __ _____ _____ _____ _____ _____  | |     __ _____ _____ __        __/  \____\\ 
 **  \    \  / /  __|  |     |   __|  _  |     |  _  | | |  __|  |     |   __|  |      /\ \  /    /  
 **   \____\/_/  |  |  |  |  |  |  |     | | | |   __| | | |  |  |  |  |  |  |  |__   "  \_\/____/   
 **  /\    \     |_____|_____|_____|__|__|_|_|_|__|    | | |_____|_____|_____|_____|  _  /    /\     
 ** /  \____\                       http://jogamp.org  |_|                              /____/  \    
 ** \  /   "' _________________________________________________________________________ `"   \  /    
 **  \/____.                                                                             .____\/     
 **
 ** Postprocessing filter inheritance root implementing no specific filter but providing
 ** a common implementation of all interface methods suitable for most convolution filter
 ** implementations.
 **
 **/

import javax.media.opengl.*;
import framework.base.*;
import framework.util.*;
import java.nio.*;

public class PostProcessingFilter_Base_Convolution extends PostProcessingFilter_Base implements BasePostProcessingFilterChainShaderInterface {

    protected String mFragmentShaderFileName;
    protected FloatBuffer mTextureCoordinateOffsets;
    protected int mFragmentShader;
    protected int mLinkedShader;

    public void initFilter(GL2 inGL) {
        mFragmentShader = ShaderUtils.loadFragmentShaderFromFile(inGL,mFragmentShaderFileName);
        mLinkedShader = ShaderUtils.generateSimple_1xFS_ShaderProgramm(inGL,mFragmentShader);
        mTextureCoordinateOffsets = generate3x3TextureCoordinateOffsets();
    }

    public void cleanupFilter(GL2 inGL) {
        inGL.glDeleteShader(mFragmentShader);
    }

    public void prepareForProgramUse(GL2 inGL) {
        inGL.glUseProgram(mLinkedShader);
        //backbuffer texture is implicitly bound to texture unit 0 ...
        ShaderUtils.setUniform1i(inGL,mLinkedShader,"sampler0",0);
        ShaderUtils.setUniform2fv(inGL,mLinkedShader,"tc_offset",mTextureCoordinateOffsets);
        inGL.glValidateProgram(mLinkedShader);
    }

    public void stopProgramUse(GL2 inGL) {
        inGL.glUseProgram(0);
    }

    protected FloatBuffer generate3x3TextureCoordinateOffsets() {
        float[] tTextureCoordinateOffsets = new float[18];
        float tXIncrease = 1.0f / ((float)BaseGlobalEnvironment.getInstance().getScreenWidth()/(float)getScreenSizeDivisionFactor());
        float tYIncrease = 1.0f / ((float)BaseGlobalEnvironment.getInstance().getScreenHeight()/(float)getScreenSizeDivisionFactor());
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                tTextureCoordinateOffsets[(((i*3)+j)*2)+0] = (-1.0f*tXIncrease)+((float)i*tXIncrease);
                tTextureCoordinateOffsets[(((i*3)+j)*2)+1] = (-1.0f*tYIncrease)+((float)j*tYIncrease);
            }
        }
        return DirectBufferUtils.createDirectFloatBuffer(tTextureCoordinateOffsets);
    }

}
