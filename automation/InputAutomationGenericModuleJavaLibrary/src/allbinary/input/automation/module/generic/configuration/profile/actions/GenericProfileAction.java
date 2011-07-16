/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.input.automation.module.generic.configuration.profile.actions;

import java.util.HashMap;

import javax.swing.JPanel;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.LogUtil;

import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import allbinary.input.automation.module.generic.configuration.profile.actions.script.GenericProfileActionScript;

public class GenericProfileAction
    implements DomNodeInterface
{
    private String name;
    
    private GenericProfileActionScript genericProfileActionScript;
    
    private GenericProfileActionJPanel genericProfileActionJPanel;
    
    public GenericProfileAction(
        GenericProfileActionJPanel genericProfileActionJPanel,
        Node node) throws Exception
    {
        Node actionNameNode = DomSearchHelper.getNode(
            GenericProfileActionData.NAME,
            node.getChildNodes());
        
        if(actionNameNode != null)
        {
            this.name =
                DomNodeHelper.getTextNodeValue(actionNameNode);
        }
        else
        {
            throw new Exception("Profile Action Name Node Null");
        }
        
        this.setGenericProfileActionScript(
            new GenericProfileActionScript(
                this.genericProfileActionJPanel, node));
    }

    public GenericProfileAction(String name) throws Exception
    {
        this.name = name;
        this.setGenericProfileActionScript(
            new GenericProfileActionScript(
                this.genericProfileActionJPanel));
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public HashMap toHashMap()
    {
        HashMap hashMap = new HashMap();
        
        hashMap.put(GenericProfileActionData.NAME, this.name);
        
        LogUtil.put("HashMap: " + hashMap.toString(), this, "toHashMap()");
        
        return hashMap;
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = ModDomHelper.createNodeWithValueNodes(
            document,
            GenericProfileActionData.NAME,
            this.toHashMap());
        
        node.appendChild(
            getGenericProfileActionScript().toXmlNode(document));

        return node;
    }

    public GenericProfileActionScript getGenericProfileActionScript()
    {
        return genericProfileActionScript;
    }

    public void setGenericProfileActionScript(GenericProfileActionScript genericProfileActionScript)
    {
        this.genericProfileActionScript = genericProfileActionScript;
    }
}
