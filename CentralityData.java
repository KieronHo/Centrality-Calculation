import java.util.ArrayList;


/**
 * Holds the data supplied for analysis, as well as the results of the four centrality calculations
 * @author Kieron Ho 20500057
 *
 */
public class CentralityData {
//Data property fields
private ArrayList<int[]> edgeList;
private ArrayList<Integer> vertexReferences;
private int numberOfVertices;
private int[][] edgeMatrix;

//Alpha value for the Katz centrality
private float alphaKatz;

//Result fields
private int[] topFiveDegree;
private int[] topFiveCloseness;
private int[] topFiveBetweenness;
private int[] topFiveKatz;


public CentralityData() {
		alphaKatz = 0.5f;//a default value for alphaKatz
		numberOfVertices = 0;
		edgeList = new ArrayList<int[]>();
		vertexReferences = new ArrayList<Integer>();
		edgeMatrix = new int[numberOfVertices][numberOfVertices];
		topFiveDegree = new int[5];
		topFiveCloseness = new int[5];
		topFiveBetweenness = new int[5];
		topFiveKatz = new int[5];
	}
	

/**
 * Clear previously stored data and set values to default
 * @return true if the clearing process was successful
 */
public boolean clearData(){
	alphaKatz = 0.5f;
	numberOfVertices = 0;
	edgeList = new ArrayList<int[]>();
	vertexReferences = new ArrayList<Integer>();
	edgeMatrix = new int[numberOfVertices][numberOfVertices];
	topFiveDegree = new int[5];
	topFiveCloseness = new int[5];
	topFiveBetweenness = new int[5];
	topFiveKatz = new int[5];
	return isEmpty();
}

/**
 * is a getter for the field edgeList
 * @return edgeList value
 */
public ArrayList<int[]> getEdgeList(){
	return edgeList;
}

/**
 * is a getter for the field vertexReferences
 * @return vertexReferences value
 */
public ArrayList<Integer> getVertexReferences(){
	return vertexReferences;
}
	
/**
 * is a getter for the field numberOfVertices
 * @return numberOfVertices value
 */
public int getNumberOfVertices(){
	return numberOfVertices;
}

/**
 * is a getter for the field edgeMatrix
 * @return edgeMatrix value
 */
public int[][] getEdgeMatrix(){
	return edgeMatrix;
}

/**
 * is a getter for the field topFiveDegree
 * @return topFiveDegree results
 */
public int[] getTopFiveDegree(){
	return topFiveDegree;
}

/**
 * is a getter for the field topFiveCloseness
 * @return topFiveCloseness results
 */
public int[] getTopFiveCloseness(){
	return topFiveCloseness;
}

/**
 * is a getter for the field topFiveBetweenness
 * @return topFiveBetweenness results
 */
public int[] getTopFiveBetweenness(){
	return topFiveBetweenness;
}

/**
 * is a getter for the field topFiveKatz
 * @return topFiveKatz results
 */
public int[] getTopFiveKatz(){
	return topFiveKatz;
}


/**
 * update the edgeList value based on new data
 * @param fileName the name of the file to extract data from
 */
private void edgeListUpdater(String fileName){
	ArrayList<int[]> newEdges = DataParser.fileToNewEdgeList(fileName);
	if(newEdges != null){
	for(int i = 0 ; i < newEdges.size(); i++){
		edgeList.add(newEdges.get(i));
	}
	}
}
	

/**
 * update the vertex reference list based on the edgeList
 */
private void vertexReferencesUpdater(){
	for(int i = 0 ; i < edgeList.size() ; i++){
		for(int j = 0 ; j < 2 ; j++){
			if(!vertexReferences.contains(edgeList.get(i)[j])){
				vertexReferences.add(edgeList.get(i)[j]);
			}
		}
	}
}

/**
 * update the number of vertices field based on the vertexReferences field
 */
private void numberOfVerticesUpdater(){
	numberOfVertices = vertexReferences.size();
}

/**
 * update the edgeMatrix based on the new edgeList
 */
private void edgeMatrixUpdater(){
	edgeMatrix = DataParser.matrixMaker(edgeList, vertexReferences, numberOfVertices);
}

/**
 * update the degreeCentrality results
 */
private void degreeCentralityUpdater(){
	topFiveDegree = DegreeCentrality.calculateDegreeCentrality(edgeMatrix, vertexReferences);
}

/**
 * update the closenessCentrality results
 */
private void closenessCentralityUpdater(){
	topFiveCloseness = ClosenessCentrality.calculateClosenessCentrality(edgeMatrix,  vertexReferences);
}

/**
 * update the betweennessCentrality results
 */
private void betweennessCentralityUpdater() {
	topFiveBetweenness = BetweennessCentrality.calculateBetweennessCentrality(edgeMatrix, vertexReferences);
}

/**
 * updates the katzCentrality results
 * @param alphaKatz is the alpha value to use in the analysis
 */
private void katzCentralityUpdater(float alphaKatz){
	topFiveKatz = KatzCentrality.calculateKatzCentrality(edgeMatrix, vertexReferences, alphaKatz);
}

/**
 * changes the alpha value used in the Katz centrality calculation
 * @param newAlphaKatz the new value for alpha
 */
private void alphaKatzChange(float newAlphaKatz){
	alphaKatz = newAlphaKatz;
}
	
/**
 * When new data is added, evaluate and update accordingly
 */
public void updateAllData(String fileName){
	int oldEdgeListSize = edgeList.size();
edgeListUpdater(fileName);
if(!isEmpty() && oldEdgeListSize != edgeList.size()){
vertexReferencesUpdater();
numberOfVerticesUpdater();
edgeMatrixUpdater();	

degreeCentralityUpdater();
closenessCentralityUpdater();
betweennessCentralityUpdater();
katzCentralityUpdater(alphaKatz);
}
}

/**
 * Modify the alpha value based on a new alpha value
 * @param newAlphaKatz is the new alpha
 */
public void alphaKatzChangeUpdate(float newAlphaKatz){
	alphaKatzChange(newAlphaKatz);
	katzCentralityUpdater(alphaKatz);
}

/**
 * check if the CentralityData Object has default values
 * @return true if tested values are equivalent to the default state
 */
public boolean isEmpty(){
	boolean emptiness = false;
	if(		alphaKatz == 0.5f
		&& numberOfVertices == 0
		&& edgeList.isEmpty()
		&& vertexReferences.isEmpty()
		&& edgeMatrix.length == 0){
		emptiness = true;
	}
	return emptiness;
}
}
