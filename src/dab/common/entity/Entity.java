package dab.common.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import org.joml.Vector2f;

import dab.common.physics.AABB;

public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 40131634443525852L;
	
	private AABB boundingBox;
	private Map<String, Boolean> modifiedAttributes;
	
	// Constructors
	public Entity() {
		this.boundingBox = new AABB();
		setupModifiedAttributesContainer();
	}
	
	// Accessors
	public Vector2f getCenter() {
		return this.boundingBox.getCenter();
	}
	
	public AABB getAABB() {
		return this.boundingBox;
	}
	
	public boolean isCenterModified() {
		return isAttributeModified("center");
	}
	
	public boolean isWidthModified() {
		return isAttributeModified("width");
	}
	
	public boolean isHeightModified() {
		return isAttributeModified("height");
	}
	
	// Mutators
	
	protected void setCenter(float x, float y) {
		this.boundingBox.setCenter(x, y);
	}
	
	protected void setCenter(Vector2f center) {
		this.boundingBox.setCenter(center);
	}
	
	protected void setWidth(float width) {
		this.boundingBox.setWidth(width);
	}
	
	protected void setHeight(float height) {
		this.boundingBox.setHeight(height);
	}
	
	public void setIsCenterModified(boolean isModified) {
		setAttributeModifiedValue("center", isModified);
	}
	
	public void setIsWidthModified(boolean isModified) {
		setAttributeModifiedValue("width", isModified);
	}
	
	public void setIsHeightModified(boolean isModified) {
		setAttributeModifiedValue("height", isModified);
	}
	
	private void setupModifiedAttributesContainer() {
		modifiedAttributes = new TreeMap<String, Boolean>();
		modifiedAttributes.put("center", false);
		modifiedAttributes.put("height", false);
		modifiedAttributes.put("width", false);
	}
	
	protected boolean isAttributeModified(String attribute) {
		return modifiedAttributes.get(attribute);
	}
	
	protected void setAttributeModifiedValue (String attribute, boolean isModified) {
		modifiedAttributes.put(attribute, isModified);
	}
	
	protected void addAttributeModifiable(String attribute) {
		modifiedAttributes.put(attribute, false);
	}
	
}
