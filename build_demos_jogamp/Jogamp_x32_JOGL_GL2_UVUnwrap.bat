echo on
cd .
set PATH=.;.\runtime_x32\bin\;
set JAVA_HOME=.\runtime_x32\;
.\runtime_x32\bin\java.exe -version
.\runtime_x32\bin\java.exe -client -Dsun.java2d.noddraw=true -Dsun.awt.noerasebackground=true -XX:+AggressiveOpts -Xcomp -Xnoclassgc -XX:+UseConcMarkSweepGC -Xms64m -Xmx1024m -jar jogamp_x32.jar -RESOLUTION=AUTO -ROUTINE=jogamp.routine.jogl.fixedfunctionpipeline.GL2_UVUnwrap -FRAMERATE=AUTO -FULLSCREEN=FALSE -VSYNC=TRUE -MULTISAMPLING=FALSE -SAMPLEBUFFERS=4 -ANISOTROPICFILTERING=TRUE -ANISOTROPYLEVEL=16.0 -FRAMESKIP=TRUE -FRAMECAPTURE=FALSE -WINDOWTOOLKIT=NEWT -MUSIC=/binaries/music/Little_Bitchard-The_Code_Inside.mp3
SET /P consolewait="PRESS RETURN TO KILL CONSOLE ..."