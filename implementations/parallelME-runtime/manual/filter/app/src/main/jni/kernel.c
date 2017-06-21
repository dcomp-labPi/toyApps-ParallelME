__kernel void firstFilter(__global int *inputVector, __global uint *inputSize){
    uint id = get_global_id(0);
    if(inputVector[id] < (inputSize[0]/2)){
        inputVector[id] = -1;
    }
}
__kernel void filter(__global int *inputVector,__global uint *inputSize,__global uint *outputVector,__global uint *outputSize){
    outputSize[0] = 0;
    for(uint i=0;i<inputSize[0];i++){
        if(inputVector[i] > 0){
            outputVector[outputSize[0]] = inputVector[i];
            outputSize[0] += 1;
        }
    }
}