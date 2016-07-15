package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Properties;

public class AnimationMeta implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 3461845179732591904L;

  private String id;

  private String desc;

  private String used;

  private Integer index;

  private Integer repeatcount;

  private Integer resolution;

  private Integer duration;

  private String displayClazz;

  private String editorClazz;

  private String type;

  private transient Object editorViewer;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getDuration() {
    return duration = duration == null ? new Integer(1000) : duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Integer getRepeatcount() {
    return repeatcount = repeatcount == null ? new Integer(2) : repeatcount;
  }

  public void setRepeatcount(Integer repeatcount) {
    this.repeatcount = repeatcount;
  }

  public Integer getResolution() {
    return resolution = resolution == null ? new Integer(1) : resolution;
  }

  public void setResolution(Integer resolution) {
    this.resolution = resolution;
  }

  public synchronized Object getEditorViewer() throws Exception {
    if (editorViewer == null) {
      if (editorClazz == null)
        editorClazz = "com.ufgov.zc.client.zc.infoscreen.console.editor.DefaultAnimationEditor";
      Class editorClass = AnimationMeta.class.getClassLoader().loadClass(editorClazz);
      editorViewer = editorClass.newInstance();
    }
    return editorViewer;
  }

  public void setEditorViewer(Object editorViewer) {
    this.editorViewer = editorViewer;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDesc() {
    return desc = desc == null ? "" : desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getUsed() {
    return used;
  }

  public void setUsed(String used) {
    this.used = used;
  }

  public Integer getIndex() {
    return index = index == null ? new Integer(1) : index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public String getDisplayClazz() {
    return displayClazz = displayClazz == null ? "" : displayClazz;
  }

  public void setDisplayClazz(String displayClazz) {
    this.displayClazz = displayClazz;
  }

  public String getEditorClazz() {
    return editorClazz = editorClazz == null ? "" : editorClazz;
  }

  public void setEditorClazz(String editorClazz) {
    this.editorClazz = editorClazz;
  }

  public AnimationMeta() {
    super();
  }

  public String toString() {
    return this.desc;
  }

  public Properties toProperties() {
    Properties p = new Properties();
    p.setProperty("resolution", this.getResolution().toString());
    p.setProperty("repeatcount", this.getRepeatcount().toString());
    p.setProperty("duration", this.getDuration().toString());
    p.setProperty("desc", this.getDesc());
    return p;
  }

}
