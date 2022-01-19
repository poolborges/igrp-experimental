/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cv.nosicode.igrpframework.buildtool.impl;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 *
 * @author pauloborges
 */
public class JavaDefaultCompiler {
    
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //These three argument will use the default System.in, System.out and System.err. 
        int result = compiler.run(null, null, null, "code_example/com/poolborges/example/javac/Hello.java");
 
        System.out.println("Compile result code = " + result);
    }
}
