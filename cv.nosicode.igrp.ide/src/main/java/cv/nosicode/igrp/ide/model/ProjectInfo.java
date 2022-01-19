/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.nosicode.igrp.ide.model;

/**
 *
 * @author pauloborges
 */
public class ProjectInfo {

    private String name;
    private String baseLocation; //ReadOnly provided by ProjectManager, repository
    private String folderName;   //ReadOnly 
    private String groupID;
    private String artifactID;
    private String basePackage;
    private String version;
}
