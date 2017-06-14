__kernel void map(uint *inputVector,uint *outputVector){
    uint id = get_global_id(0);
    outputVector[id] += inputVector[id]*inputVector[id];
}