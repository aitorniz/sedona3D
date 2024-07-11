package FormatMapper;

import org.apache.sedona.core.formatMapper.PointFormatMapper;
import org.apache.sedona.common.enums.FileDataSplitter;
import GeometryType3D.POINT3D;

public class Point3DFormatMapper extends PointFormatMapper{
	 /**
   	* Instantiates a new point format mapper.
   	*
        * @param Splitter the splitter
	* @param carryInputData the carry input data
        */
	
  	public Point3DFormatMapper(FileDataSplitter Splitter, boolean carryInputData) {
    	    super(0, 1, Splitter, carryInputData, GeometryType3D.POINT3D);
  	}
	/**
        * Instantiates a new point format mapper.
        *
        * @param startOffset the start offset
        * @param Splitter the splitter
        * @param carryInputData the carry input data
        */
	
	public Point3DFormatMapper(Integer startOffset, FileDataSplitter Splitter, boolean carryInputData) {
	    super(startOffset, startOffset + 1, Splitter, carryInputData, GeometryType3D.POINT3D);

	}
}


