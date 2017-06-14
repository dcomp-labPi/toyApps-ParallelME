__kernel void foreach(uint *inputVector){
    uint id = get_global_id(0);
    inputVector[id] = inputVector[id]*inputVector[id];
}