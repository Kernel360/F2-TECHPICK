import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import { resolve } from 'path';
import { viteStaticCopy } from 'vite-plugin-static-copy';
import { vanillaExtractPlugin } from '@vanilla-extract/vite-plugin';
import tsconfigPaths from 'vite-tsconfig-paths';

// https://vitejs.dev/config/
export default defineConfig({
  build: {
    watch: {
      include: 'src/**',
      exclude: 'node_modules/**',
    },
    rollupOptions: {
      input: {
        background: resolve(__dirname, 'src/chorme-extension/background.ts'),
        popup: resolve(__dirname, './index.html'),
      },
      output: {
        entryFileNames: '[name].js',
      },
    },
    outDir: 'dist',
  },
  plugins: [
    react(),
    viteStaticCopy({
      targets: [{ src: './src/chorme-extension/manifest.json', dest: '.' }],
    }),
    vanillaExtractPlugin(),
    tsconfigPaths(),
  ],
});
