__kernel void reduce(uint *inputVector,uint *size,uint *result){
    uint id = get_global_id(0);
    result[0] = 0;
    for(uint i=0; i<size[0];i++){
        if(inputVector[i] > result[0]){
            result[0] = inputVector[i];
        }
    }
}