def set_operations():
    A = {'a', 'g', 'd', 'f', 'c', 'b'}
    B = {'b', 'l', 'm', 'o', 'h', 'c', 'd', 'f'}
    C = {'h', 'k', 'i', 'j', 'c', 'd', 'f'}
    
    results = {
        'Elements in A and B': len(A | B),
        'Elements in B but not A and C': len(B - (A | C)),
        'Set_i': C - (A | B),
        'Set_ii': A & B & C,
        'Set_iii': (A & B) | (B & C),
        'Set_iv': (A & B) - C,
        'Set_v': A & B & C,
        'Set_vi': B - (A | C)
    }
    print("\nSet Operation Results:")
    for key, value in results.items():
        print(f"{key}: {value}")

if __name__ == "__main__":
    set_operations()
