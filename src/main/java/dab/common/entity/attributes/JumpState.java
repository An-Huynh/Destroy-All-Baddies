package dab.common.entity.attributes;

public enum JumpState
{	
	GROUND(0.0f),
	ARC1(4.5f),	
	ARC2(4.25f),
	ARC3(4.0f),
	ARC4(3.75f),
	ARC5(3.5f),
	ARC6(3.25f),
	ARC7(3.0f),
	ARC8(2.75f),
	ARC9(2.5f),
	ARC10(2.25f),
	ARC11(2.0f),
	ARC12(1.75f),
	ARC13(1.5f),
	ARC14(1.25f),
	ARC15(1.0f),
	ARC16(0.75f),
	ARC17(0.5f);
	
	private float distanceModifier;
	
	JumpState(float distanceModifier)
	{
		this.distanceModifier = distanceModifier;
	}
	
	public float getDistanceModifier()
	{
		return distanceModifier;
	}
	
	public JumpState getNext()
	{
		JumpState next;
		switch(this)
		{
			case GROUND: next = ARC1;   break;
			case ARC1:   next = ARC2;   break;
			case ARC2:   next = ARC3;   break;
			case ARC3:   next = ARC4;   break;
			case ARC4:   next = ARC5;   break;
			case ARC5:   next = ARC6;   break;
			case ARC6:   next = ARC7;   break;
			case ARC7:   next = ARC8;   break;
			case ARC8:   next = ARC9;   break;
			case ARC9:   next = ARC10;  break;
			case ARC10:  next = ARC11;  break;
			case ARC11:  next = ARC12;  break;
			case ARC12:  next = ARC13;  break;
			case ARC13:  next = ARC14;  break;
			case ARC14:  next = ARC15;  break;
			case ARC15:  next = ARC16;  break;
			case ARC16:  next = ARC17;  break;
			case ARC17:  next = GROUND; break;
			default: next = GROUND;
		}
		return next;
	}
}
