echo on
cd .
set PATH=.;.\runtime\bin\;.\native\;
set JAVA_HOME=.\runtime\;
.\runtime\bin\java.exe -version
.\runtime\bin\java.exe -client -Dsun.java2d.noddraw=true -Dsun.awt.noerasebackground=true -XX:+AggressiveOpts -Xcomp -Xnoclassgc -XX:+UseConcMarkSweepGC -Xms64m -Xmx512m -jar jogamp.jar -RESOLUTION=AUTO -ROUTINE=jogamp.routine.jogl.programmablepipeline.GL3_BreakpointAccident -FRAMERATE=AUTO -FULLSCREEN=TRUE -VSYNC=TRUE -MULTISAMPLING=FALSE -SAMPLEBUFFERS=4 -ANISOTROPICFILTERING=TRUE -ANISOTROPYLEVEL=16.0 -FRAMESKIP=TRUE -FRAMECAPTURE=FALSE -WINDOWTOOLKIT=AWT -MUSIC=/binaries/music/Teo-Fractal_Warrior.mp3
SET /P consolewait="PRESS RETURN TO KILL CONSOLE ..."