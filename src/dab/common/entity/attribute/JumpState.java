package dab.common.entity.attribute;

public enum JumpState {
	
	GROUND(0.0f),
	READY(5.5f),
	ARC1(5.0f),
	ARC2(4.5f),
	ARC3(4.0f),
	ARC4(3.5f),	
	ARC5(3.0f),
	ARC6(2.5f),
	ARC7(2.0f),
	ARC8(1.5f),
	ARC9(1.0f),
	ARC10(0.5f);
	private float jumpDistanceModifier;
	
	JumpState(float jumpDistanceModifier) {
		this.jumpDistanceModifier = jumpDistanceModifier;
	}
	
	public float getJumpDistanceModifier() {
		return jumpDistanceModifier;
	}
	
	public JumpState getNextState() {
		switch (this) {
			case GROUND:
				return READY;
			case READY:
				return ARC1;
			case ARC1:
				return ARC2;
			case ARC2:
				return ARC3;
			case ARC3:
				return ARC4;
			case ARC4:
				return ARC5;
			case ARC5:
				return ARC6;
			case ARC6:
				return ARC7;
			case ARC7:
				return ARC8;
			case ARC8:
				return ARC9;
			case ARC9:
				return ARC10;
			case ARC10:
				return GROUND;
			default:
				return GROUND;
		}
	}
	
}
