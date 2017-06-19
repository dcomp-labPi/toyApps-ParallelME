__kernel void map(__global uint *inputVector,__global uint *outputVector){
    uint id = get_global_id(0);
    outputVector[id] += inputVector[id]*inputVector[id];
}