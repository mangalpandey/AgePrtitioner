package solution;

import java.io.IOException;
//import java.util.StringTokenizer;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PartitionerMapper extends Mapper<LongWritable, Text, Text, Text> {
	// private final static IntWritable countOne = new IntWritable(1);
	// private final Text reusableText = new Text();
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// sample record
		// name,age,gender,salary
		// Raju,23,male,5000
		// Rani,21,female,50000
		String[] tokens = value.toString().split(",");
		String gender = tokens[2].toString();

		String nameAgeSalary = tokens[0] + "," + tokens[1] + "," + tokens[3];
		// the mapper emits key, value pair where the key is the gender and the value is
		// the other information which includes name, age and score
		context.write(new Text(gender), new Text(nameAgeSalary));
	}
}