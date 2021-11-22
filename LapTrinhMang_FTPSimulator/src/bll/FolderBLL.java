/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import bll.helpers.DateHelper;
import bll.helpers.FileExtensions;
import bll.helpers.ThreadRandoms;
import dal.Services.FolderServices;
import models.Folders;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class FolderBLL {

    public FolderBLL() {
    }

    public boolean generateFolderChild(Folders folder) {
        if (FileExtensions.generateFolder(folder.getFolderPath())) {
            if (new FolderServices().Create(folder)) {
                return true;
            }
        }
        return false;
    }
}
