
import org.apache.sedona.core.formatMapper.PointFormatMapper
import 
public class Point3DFormatMapper extends PointFormatMapper{
	@override
	public PointFormatMapper(Integer startOffset, FileDataSplitter Splitter, boolean carryInputData) {
	    super(startOffset, startOffset + 1, Splitter, carryInputData, GeometryType.POINT3D);

	}


