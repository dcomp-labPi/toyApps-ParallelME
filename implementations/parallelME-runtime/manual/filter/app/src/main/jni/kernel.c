__kernel void firstFilter(__global int *inputVector, __global uint *inputSize){
    uint id = get_global_id(0);
    uint save = 0;
    for(uint i=0;i<inputSize[0];i++){
        if(inputVector[id] > inputVector[i]){
            save = 1;
            break;
        }
    }
    if(save != 1){
        inputVector[id] = -1;
    }
}
__kernel void filter(int *inputVector,uint *inputSize, uint *outputVector,uint *outputSize){
    outputSize[0] = 0;
    for(uint i=0;i<inputSize[0];i++){
        if(inputVector[i] == 1){
            outputVector[outputSize[0]] = inputVector[i];
            outputSize[0] += 1;
        }
    }
}